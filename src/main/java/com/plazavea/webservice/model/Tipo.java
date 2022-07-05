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
@Table(name = "tipo")
public class Tipo {

    @Id
    @SequenceGenerator(name = "tipo_seq", sequenceName = "tipo_seq", allocationSize = 1)
    @GeneratedValue(generator = "tipo_seq")
    private int idTipo;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_subcategoria) references subcategoria(id_subcategoria)"))
    private SubCategoria subcategoria;

    @OneToMany(mappedBy = "tipo",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Subtipo> subtipos;

    public void saveChilds(){
        this.subtipos = subtipos.stream().map(x->{
            x.setTipo(this);
            return x;
        }).collect(Collectors.toList());
    }
    
}
