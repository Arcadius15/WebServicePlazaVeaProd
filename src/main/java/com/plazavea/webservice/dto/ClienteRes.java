package com.plazavea.webservice.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ClienteRes {
    private String idCliente;
    private String nombre;
    private String apellidos;
    private String dni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String numTelefonico;
    @JsonInclude(Include.NON_EMPTY)
    private List<DireccionCliRes> direcciones;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class DireccionCliRes{
    private String direccion;
    private Double latitud;
    private Double longitud;
}
