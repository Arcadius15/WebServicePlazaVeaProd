package com.plazavea.webservice.dto;

import java.time.LocalTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TiendaReq {
    @NotBlank
    private String nombre;
    @NotBlank
    private String direccion;
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotBlank
    private String numeroTelefonico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioA;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioC;
    @Valid
    private EmpleadoTRes empleado;
}

@Data
class EmpleadoTRes{
    @NotEmpty
    @NotBlank
    @NotNull
    private String idEmpleado;
}
