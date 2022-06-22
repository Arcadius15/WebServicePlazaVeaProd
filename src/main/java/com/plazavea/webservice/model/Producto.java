package com.plazavea.webservice.model;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "producto")
@JsonIgnoreProperties({"proveedor", "subtipo", "productosxtienda", "ordendetalle", "pedidodetalle"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "producto_seq")
    @GenericGenerator(name = "producto_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "P_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idProducto;
    @Column
    private String nombre;
    @Column
    private String imagenUrl;
    @Column
    private Double precioRegular;
    @Column
    private Double precioOferta;
    @Column
    private boolean oferta;

    
    @ManyToOne
    @JoinColumn(name = "id_proveedor",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_proveedor) references proveedor(id_proveedor)"))
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_subtipo",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_subtipo) references subtipo(id_subtipo)"))
    private Subtipo subtipo;

    @OneToMany(mappedBy = "producto",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<ProductoTienda> productosxtienda;

    @JsonIgnore
    @OneToMany(mappedBy = "producto",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<OrdenDetalle> ordendetalle;

    @JsonIgnore
    @OneToMany(mappedBy = "producto",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<PedidoDetalle> pedidodetalle;

    @OneToMany(mappedBy = "producto",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Descripcion> descripciones;

    @OneToMany(mappedBy = "producto",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Especificaciones> especificaciones;


    @OneToMany(mappedBy = "producto",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Promocion> promociones;
    
}
