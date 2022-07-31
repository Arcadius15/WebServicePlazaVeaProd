package com.plazavea.webservice.security.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;

import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.model.Repartidor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioReq {
    @Email
    private String email;
    @NotEmpty
    private String password;

    private Set<String> roles;
    
    @Valid
    @Nullable
    private Cliente cliente;
    
    @Valid
    @Nullable
    private Empleado empleado;
    
    @Valid
    @Nullable
    private Repartidor repartidor;
    
    public UsuarioReq(String email, String psw,Set<String> roles){
        this.email = email;
        this.password = psw;
        this.roles = roles;
    }
}
