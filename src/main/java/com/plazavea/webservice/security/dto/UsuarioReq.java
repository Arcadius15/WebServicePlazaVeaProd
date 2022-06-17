package com.plazavea.webservice.security.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.model.Repartidor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioReq {
    @Email
    private String email;
    @NotEmpty
    private String password;
    private Set<String> roles;
    private Cliente cliente;
    private Empleado empleado;
    private Repartidor repartidor;
}
