package com.plazavea.webservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class TarjetaReq {
    private int tipo;
    private String numeroTarjeta;
    private String cvv;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCaducidad;
    private String nombrePropietario;
    private ClienteTReq cliente;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ClienteTReq{
    private String idCliente;
}
