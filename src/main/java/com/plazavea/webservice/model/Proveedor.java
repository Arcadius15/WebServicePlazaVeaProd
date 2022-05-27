package com.plazavea.webservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;
    @Column
    private String nombreCompania;
    @Column
    private String nombreContacto;
    @Column
    private String numeroContacto;
    @Column
    private String direccion;
    @Column
    private String codigoPostal;
    @Column
    private String paginaWeb;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;
}
