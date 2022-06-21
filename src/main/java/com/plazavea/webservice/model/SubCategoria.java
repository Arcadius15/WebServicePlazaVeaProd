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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "subcategoria")
public class SubCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubcategoria;
    @Column
    private String nombre;
    private String urlFoto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_categoria",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_categoria) references categoria(id_categoria)"))
    private Categoria categoria;

    @JsonIgnoreProperties({"subcategoria"})
    @OneToMany(mappedBy = "subcategoria",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Tipo> tipos;
    
}
