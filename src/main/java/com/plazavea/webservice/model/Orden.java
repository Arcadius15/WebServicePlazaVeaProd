package com.plazavea.webservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.enums.OrdenStatus;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orden_seq")
    @GenericGenerator(name = "orden_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "ORD_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idOrden;
    @Column
    private Double monto;
    @Column
    private Double igv;
    @Column
    private Double total;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate fecha;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate fechaEntrega;
    @Column
    private String direccion;
    @Column
    private String formaPago;
    @Column
    private int tipoFop;
    @Enumerated(EnumType.STRING)
    private OrdenStatus status;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_cliente) references cliente(id_cliente)"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tienda",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tienda) references tienda(id_tienda)"))
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "id_repartidor",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_repartidor) references repartidor(id_repartidor)"))
    private Repartidor repartidor;

    @OneToMany(mappedBy = "orden",cascade = CascadeType.ALL)
    private Set<OrdenDetalle> ordendetalle;

    @OneToMany(mappedBy = "orden",cascade = CascadeType.ALL)
    private List<HistorialOrden> historial;
    
}
