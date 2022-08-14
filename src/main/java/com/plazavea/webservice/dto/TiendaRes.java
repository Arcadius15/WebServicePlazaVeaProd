package com.plazavea.webservice.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TiendaRes {
    private String idTienda;
    private String nombre;
    private String direccion;
    private Double lat;
    private Double lng;
    private String numeroTelefonico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horarioA;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horarioC;
    private String gerente;
}
