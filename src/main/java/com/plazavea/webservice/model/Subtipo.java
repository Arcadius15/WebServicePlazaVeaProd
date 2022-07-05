package com.plazavea.webservice.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "subtipo")
public class Subtipo {
    
    @Id
    @SequenceGenerator(name = "subtipo_seq", sequenceName = "subtipo_seq", allocationSize = 1)
    @GeneratedValue(generator = "subtipo_seq")
    private int idSubtipo;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tipo) references tipo(id_tipo)"))
    private Tipo tipo;

    @OneToMany(mappedBy = "subtipo",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Producto> productos;

    public void saveChilds(){
        this.productos = productos.stream().map(x->{
            x.setSubtipo(this);
            return x;
        }).collect(Collectors.toList());
    }

}
