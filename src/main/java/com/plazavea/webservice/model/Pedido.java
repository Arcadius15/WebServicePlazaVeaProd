package com.plazavea.webservice.model;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.plazavea.webservice.enums.OrdenStatus;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pedido_seq")
    @GenericGenerator(name = "pedido_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "PED_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idPedido;
    @Column
    private Double monto;
    @Column
    private Double igv;
    @Column
    private Double total;
    @Column
    private String ruc;
    @Enumerated(EnumType.STRING)
    private OrdenStatus estado;


    @ManyToOne
    @JoinColumn(name = "id_admin",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_admin) references admin(id_admin)"))
    private Admin admin;
    
    @ManyToOne
    @JoinColumn(name = "id_tienda",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tienda) references tienda(id_tienda)"))
    private Tienda tienda;

    @OneToMany(mappedBy = "pedido",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<PedidoHistorial> historial;

    @OneToMany(mappedBy = "pedido",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<PedidoDetalle> pedido_detalle;
}
