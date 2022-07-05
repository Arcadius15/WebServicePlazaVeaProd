package com.plazavea.webservice.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @SequenceGenerator(name = "cat_seq", sequenceName = "cat_seq", allocationSize = 1)
    @GeneratedValue(generator = "cat_seq")
    private int idCategoria;
    @Column
    private String nombre;

    @OneToMany(mappedBy = "categoria",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SubCategoria> subcategorias;

    public void saveChilds(){
        this.subcategorias = subcategorias.stream().map(x->{
            x.setCategoria(this);
            return x;
        }).collect(Collectors.toList());
    }
}
