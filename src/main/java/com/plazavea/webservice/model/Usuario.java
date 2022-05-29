package com.plazavea.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private int estado;

    @OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Repartidor repartidor;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Admin admin;
    
}
