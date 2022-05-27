package com.plazavea.webservice.model;

import java.util.List;

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
@Table(name = "subtipo")
public class Subtipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubtipo;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tipo) references tipo(id_tipo)"))
    private Tipo tipo;

    @OneToMany(mappedBy = "subtipo")
    private List<Producto> productos;

}
