package com.plazavea.webservice.model;

import java.time.LocalTime;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tienda")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTienda;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String numeroTelefonico;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioA;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioC;

    @ManyToOne
    @JoinColumn(name = "id_admin",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_admin) references admin(id_admin)"))
    private Admin admin;
    
    @OneToMany(mappedBy = "tienda")
    private Set<ProductoTienda> productosxtienda;

    @OneToMany(mappedBy = "tienda")
    private List<Orden> ordenes;
}
