package com.plazavea.webservice.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @Column
    private String nombre;
    @Column
    private String marca;
    @Column
    private String codigo;
    @Column
    private Double precioRegular;
    @Column
    private Double precioOferta;
    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_proveedor",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_proveedor) references proveedor(id_proveedor)"))
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_subtipo",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_subtipo) references subtipo(id_subtipo)"))
    private Subtipo subtipo;

    @OneToMany(mappedBy = "producto")
    private Set<ProductoTienda> productosxtienda;

    @OneToMany(mappedBy = "producto")
    private Set<OrdenDetalle> ordendetalle;
    
    
}
