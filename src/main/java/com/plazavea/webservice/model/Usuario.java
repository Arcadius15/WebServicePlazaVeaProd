package com.plazavea.webservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usuario_seq")
    @GenericGenerator(name = "usuario_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "USR_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private int estado;

    @OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Repartidor repartidor;

    @OneToOne(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Admin admin;

    @ManyToMany
    @JoinTable(name="rol_usuario",
        joinColumns = @JoinColumn(name="id_usuario",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_usuario) references usuario(id_usuario)")),
        inverseJoinColumns = @JoinColumn(name="id_rol",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_rol) references rol(id_rol)")))
    private Set<Rol> roles;
    
}
