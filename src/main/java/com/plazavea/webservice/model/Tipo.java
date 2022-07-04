package com.plazavea.webservice.model;

import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int idTipo;
    @Column
    private String nombre;

    @ManyToOne
    @JsonIgnoreProperties({"tipos"})
    @JoinColumn(name = "id_subcategoria",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_subcategoria) references subcategoria(id_subcategoria)"))
    private SubCategoria subcategoria;

    @JsonIgnoreProperties({"tipo","productos"})
    @OneToMany(mappedBy = "tipo",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Subtipo> subtipos;
    
}
