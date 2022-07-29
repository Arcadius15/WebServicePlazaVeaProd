package com.plazavea.webservice.security.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.plazavea.webservice.enums.RepartidorStatus;
import com.plazavea.webservice.security.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRes {
    private String idUsuario;
    private String email;
    private boolean activo;
    private LocalDate pswExp;
    private boolean blocked;
    private Set<RolURes> roles;
    @JsonInclude(Include.NON_NULL)
    private ClienteURes cliente;
    @JsonInclude(Include.NON_NULL)
    private EmpleadoURes empleado;
    @JsonInclude(Include.NON_NULL)
    private RepartidorURes repartidor;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ClienteURes{
    private String idCliente;
    private String nombre;
    private String apellidos;
    private String dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaNacimiento;
    private String numTelefonico;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class EmpleadoURes{
    private String idEmpleado;
    private String nombres;
    private String apellidos;
    private String dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaNacimiento;
    private String numTelefonico;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class RepartidorURes{
    private String idRepartidor;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaNacimiento;
    private String numTelefonico;
    private String placa;
    private int turno;
    @Enumerated(EnumType.STRING)
    private RepartidorStatus status;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class RolURes{
    @Enumerated(EnumType.STRING)
    private Roles rol;
}