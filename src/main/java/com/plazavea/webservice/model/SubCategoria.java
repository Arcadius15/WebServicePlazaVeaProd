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
@Table(name = "subcategoria")
public class SubCategoria {

    @Id
    @SequenceGenerator(name = "subcat_seq", sequenceName = "subcat_seq", allocationSize = 1)
    @GeneratedValue(generator = "subcat_seq")
    private int idSubcategoria;
    @Column
    private String nombre;
    private String urlFoto;

    @ManyToOne
    @JoinColumn(name = "id_categoria",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_categoria) references categoria(id_categoria)"))
    private Categoria categoria;

    @OneToMany(mappedBy = "subcategoria",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Tipo> tipos;

    public void saveChilds(){
        this.tipos = tipos.stream().map(x->{
            x.setSubcategoria(this);
            return x;
        }).collect(Collectors.toList());
    }
    
}
