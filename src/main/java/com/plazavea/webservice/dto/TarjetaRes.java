package com.plazavea.webservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TarjetaRes {
    private int idTarjeta;
    private int tipo;
    private String numeroTarjeta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCaducidad;
    private String nombrePropietario;
}
