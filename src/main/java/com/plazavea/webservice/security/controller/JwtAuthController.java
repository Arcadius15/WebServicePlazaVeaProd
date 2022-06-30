package com.plazavea.webservice.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazavea.webservice.security.dto.TokenRes;
import com.plazavea.webservice.security.dto.UsuarioReq;
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
    
    
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReq authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
		
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
                Usuario userSave = service.save(user);
                if (userSave!=null) {
                    return ResponseEntity.ok(userSave); 
                }
            }
        }
        return ResponseEntity.badRequest().body(new Mensaje("No se definio tipo de usuario"));
	}

    @PostMapping(value = "/registro/empleado")
	public ResponseEntity<?> saveEmpleado(@Valid @RequestBody UsuarioReq user,BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos o email inválido"));
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
            return ResponseEntity.ok(userSave); 
        }
        return ResponseEntity.badRequest().body(new Mensaje("Usuario Invalido"));
	}



    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
