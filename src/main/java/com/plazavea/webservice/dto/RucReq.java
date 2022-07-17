package com.plazavea.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RucReq {
    @NotEmpty
    @NotNull
    private String numeroRuc;
    @Valid
    private ClienteRucReq cliente;
}

@Data
class ClienteRucReq{
    @NotEmpty
    @NotNull
    private String idCliente;
}
