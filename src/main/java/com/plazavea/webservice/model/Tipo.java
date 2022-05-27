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
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipo;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_subcategoria) references subcategoria(id_subcategoria)"))
    private SubCategoria subcategoria;

    @OneToMany(mappedBy = "tipo")
    private List<Subtipo> subtipos;
    
}
