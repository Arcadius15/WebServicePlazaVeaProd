package com.plazavea.webservice.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.enums.RepartidorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RepartidorRes {

    private String idRepartidor;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String numTelefonico;
    private byte[] foto;
    private String placa;
    private int turno;
    @Enumerated(EnumType.STRING)
    private RepartidorStatus status;
    
}
