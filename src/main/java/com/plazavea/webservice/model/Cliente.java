package com.plazavea.webservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellidos;
    private int dni;
    private String direccion;
    private LocalDate fechaNacimiento;
    private int numTelefonico;
}
