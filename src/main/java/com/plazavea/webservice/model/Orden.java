package com.plazavea.webservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

import lombok.Data;

@Data
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrden;
    @Column
    private Double monto;
    @Column
    private Double igv;
    @Column
    private Double total;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;
    @Column
    private String direccion;
    @Column
    private String formaPago;
    @Column
    private int tipoFop;
    
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

    @OneToMany(mappedBy = "orden")
    private Set<OrdenDetalle> ordendetalle;

    @OneToMany(mappedBy = "orden")
    private List<HistorialOrden> historial;
    
}
