package com.plazavea.webservice.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ClienteRes {
    private String idCliente;
    private String nombre;
    private String apellidos;
    private String dni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String numTelefonico;
    @JsonInclude(content = Include.NON_EMPTY )
    private Set<DireccionCliRes> direcciones;


}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class DireccionCliRes{
    private String direccion;
    private Long latitud;
    private Long longitud;
}
