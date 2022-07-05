package com.plazavea.webservice.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteRes {
    private String idCliente;
    private String nombre;
    private String apellidos;
    private String dni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Pattern(regexp="(^$|[0-9]{9})")
    private String numTelefonico;  
}
