package com.plazavea.webservice.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtReq {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}
