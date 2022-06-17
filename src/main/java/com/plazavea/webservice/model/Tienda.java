package com.plazavea.webservice.model;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name = "tienda")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tienda_seq")
    @GenericGenerator(name = "tienda_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "T_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idTienda;
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
    @JoinColumn(name = "id_empleado",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_empleado) references empleado(id_empleado)"))
    private Empleado empleado;
    
    @OneToMany(mappedBy = "tienda",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<ProductoTienda> productosxtienda;

    @OneToMany(mappedBy = "tienda")
    private List<Orden> ordenes;

    @OneToMany(mappedBy = "tienda",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Pedido> pedidos;
}
