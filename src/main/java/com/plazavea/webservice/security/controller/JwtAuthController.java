package com.plazavea.webservice.security.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazavea.webservice.security.dto.TokenRes;
import com.plazavea.webservice.security.dto.UsuarioPsw;
import com.plazavea.webservice.security.dto.UsuarioReq;
import com.plazavea.webservice.security.dto.UsuarioRes;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.dto.Mensaje;
import com.plazavea.webservice.security.dto.JwtReq;
import com.plazavea.webservice.security.service.UserDetService;
import com.plazavea.webservice.security.service.UsuarioServ;
import com.plazavea.webservice.security.utils.JwtUtil;

@RestController
@RequestMapping("/jwt")
@CrossOrigin
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetService service;

    @Autowired
    private UsuarioServ usuarioServ;

    @Autowired
    private ModelMapper mapper;
    
    
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReq authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
        final Usuario userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
		
		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new TokenRes(token));
	}

    @PostMapping(value = "/registro")
	public ResponseEntity<?> saveCliente(@Valid @RequestBody UsuarioReq user,BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos o email inválido"));
        if(usuarioServ.existByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body(new Mensaje("email ya existe"));
        for (String rol : user.getRoles()) {
            if (rol.toLowerCase().equals("cliente")) {
                Usuario u = service.save(user);
                UsuarioRes userSave = mapper.map( u, UsuarioRes.class);
                if (userSave!=null) {
                    return ResponseEntity.ok(userSave); 
                }
            }
        }
        return ResponseEntity.badRequest().body(new Mensaje("No se definio tipo de usuario"));
	}

    @PutMapping(value = "/editpassword")
    public ResponseEntity<?> editPasswordCliente(@Valid @RequestBody UsuarioPsw user,BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos o email inválido"));
        if(!usuarioServ.existByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body(new Mensaje("email no existe"));
        Usuario usuario =  service.editPsw(user);
        if (usuario!=null) {
            return ResponseEntity.ok(new Mensaje("Contraseña Actualizada exitosamente"));
        }
        return ResponseEntity.badRequest().body(new Mensaje("Contraseña Invalida"));

    }

    @PostMapping(value = "/registro/empleado")
	public ResponseEntity<?> saveEmpleado(@Valid @RequestBody UsuarioReq user,BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(
                new Mensaje("Campos mal ingresados: "+
                    ((FieldError) bindingResult.getAllErrors().get(0)).getField() + " -> " +
                    bindingResult.getAllErrors().get(0).getDefaultMessage())
                );
        if(usuarioServ.existByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body(new Mensaje("email ya existe"));
        if(user.getRoles().size()>1)
            return ResponseEntity.badRequest().body(new Mensaje("Solo se puede ingresar un Rol por Usuario"));
        Usuario userSave = null;
        for (String rol : user.getRoles()) {
            if (rol.toLowerCase().equals("empleado")) {
                userSave = service.save(user);
                break;
            }else if(rol.toLowerCase().equals("repartidor")){
                userSave = service.save(user);
                break;
            }else if(rol.toLowerCase().equals("admin")){
                userSave = service.save(user);
                break;
            }
        }
        if (userSave!=null) {
            UsuarioRes userRes = mapper.map(userSave, UsuarioRes.class);
            return ResponseEntity.ok(userRes); 
        }
        return ResponseEntity.badRequest().body(new Mensaje("Usuario Invalido"));
	}

    @PostMapping(value = "/getuserdetails")
    public ResponseEntity<?> getUsuario(@Valid @RequestBody UsuarioReq user){
        try {
            authenticate(user.getEmail(), user.getPassword());
            if(usuarioServ.existByEmail(user.getEmail())){
                UsuarioRes u = mapper.map(usuarioServ.getByEmail(user.getEmail()).get(), UsuarioRes.class) ;
                return ResponseEntity.ok().body(u);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Email o Contraseña invalidos"));
        }
        
    }





    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}catch(CredentialsExpiredException e){
            throw new Exception("EXPIRED_CREDENTIALS", e);
        }
	}
}
