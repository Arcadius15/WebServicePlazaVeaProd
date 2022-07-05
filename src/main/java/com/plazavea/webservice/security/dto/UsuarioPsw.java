package com.plazavea.webservice.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UsuarioPsw {

    @Email
    private String email;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    
}
