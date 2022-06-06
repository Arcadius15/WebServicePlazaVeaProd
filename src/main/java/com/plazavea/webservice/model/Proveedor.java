package com.plazavea.webservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "proveedor_seq")
    @GenericGenerator(name = "proveedor_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "PRO_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idProveedor;
    @Column
    private String nombreCompania;
    @Column
    private String nombreContacto;
    @Column
    private String numeroContacto;
    @Column
    private String direccion;
    @Column
    private String codigoPostal;
    @Column
    private String paginaWeb;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;
}
