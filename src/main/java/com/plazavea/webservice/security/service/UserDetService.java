package com.plazavea.webservice.security.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.security.dto.UsuarioPsw;
import com.plazavea.webservice.security.dto.UsuarioReq;
import com.plazavea.webservice.security.enums.Domains;
import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.ConfirmationToken;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.security.repository.UsuarioRepository;
import com.plazavea.webservice.security.utils.AccountStatusUserDetailsChecker;

import lombok.extern.slf4j.Slf4j;

import static java.util.Optional.ofNullable;

import java.io.File;

@Slf4j
@Service
public class UserDetService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RolServ rolServ;

    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return
        ofNullable(email)
                .flatMap(un -> repository.findByEmail(un))
                .map(u ->  {
                    new AccountStatusUserDetailsChecker().check(u);
                    return u;
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found in database", email)));
    }

    @Transactional
    public Usuario save(UsuarioReq userReq)throws Exception{
        Usuario user = new Usuario();
        user.setEmail(userReq.getEmail());
        user.setPassword(encriptador().encode(userReq.getPassword()));
        user.setBlocked(false);
        user.setActivo(false);
        user.setPswExp(LocalDate.now().plusMonths(1));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.getByRolNombre(Roles.USER).get());
        for (String rolReq : userReq.getRoles()) {
            switch (rolReq.toLowerCase()) {
                case "repartidor":
                    if (userReq.getRepartidor()!=null) {
                        user.setRepartidor(userReq.getRepartidor());
                        user.getRepartidor().setUsuario(user);
                        roles.add(rolServ.getByRolNombre(Roles.DELIVERY).get());
                    }
                    break;
                case "cliente":
                    if (userReq.getCliente()!=null) {
                        user.setCliente(userReq.getCliente());
                        user.getCliente().setUsuario(user);
                        roles.add(rolServ.getByRolNombre(Roles.CLIENTE).get());
                    }
                break;
                case "empleado":
                    if (userReq.getEmpleado()!=null) {
                        user.setEmpleado(userReq.getEmpleado());
                        user.getEmpleado().setUsuario(user);
                        roles.add(rolServ.getByRolNombre(Roles.EMPLEADO).get());
                    }
                break;
                case "admin":
                    if (userReq.getEmpleado()!=null) {
                        user.setEmpleado(userReq.getEmpleado());
                        user.getEmpleado().setUsuario(user);
                        roles.add(rolServ.getByRolNombre(Roles.ADMIN).get());
                    }
                break;
                case "master":
                    if (repository.findByRoles_Rol(Roles.MASTER).size()<1) {
                        roles.add(rolServ.getByRolNombre(Roles.MASTER).get());
                    }
                break;
            }
        }
        if (roles.size()==1 || roles.size()>2) {
            return null;
        }
        user.setRoles(roles);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        user.setConfirmationToken(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("diegovic996@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
            +"https://plazavea-webservice.herokuapp.com/confirm-account?token="+confirmationToken.getConfirmationToken());
        var sendedMail = false;
        try {
            
            for (Domains domains : Domains.values()) {
                if (user.getEmail().contains("@"+domains.name().toLowerCase()+".com")) {
                    emailSenderService.getMessage(mailMessage);
                    sendedMail = true;
                    break;
                }
            }
            if (!sendedMail) {
                throw new Exception("Error en Dominio");
            }
        } catch (Exception e) {
            File carpeta = new File("tokens/StoredCredential");
            if (carpeta.exists()) {
                var del =carpeta.delete();
                log.error(del?"Archivo TOKEN Borrado":"Error");
            }
            return null;
        }
        if(sendedMail) return repository.save(user); else return null;
    }

    @Transactional
    public Usuario editPsw(UsuarioPsw userPsw){
        Usuario usuario = repository.findByEmail(userPsw.getEmail()).get();
        if (!encriptador().matches(userPsw.getOldPassword(), usuario.getPassword())) {
            return null;
        };
        usuario.setPassword(encriptador().encode(userPsw.getNewPassword()));
        usuario.setPswExp(LocalDate.now().plusMonths(1));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userPsw.getEmail());
            mailMessage.setSubject("Contraseña Cambiada Exitosamente!");
            mailMessage.setFrom("diegovic996@gmail.com");
            mailMessage.setText("Su Contraseña fue cambiada exitosamente, su fecha de expiracion fue cambiada a "
            +usuario.getPswExp().toString());
        
        try {
            emailSenderService.getMessage(mailMessage);
        } catch (Exception e) {
            return null;
        }
        return repository.saveAndFlush(usuario);
    }

    @Bean
	public PasswordEncoder encriptador() {
		return new BCryptPasswordEncoder();
	}
    
}
