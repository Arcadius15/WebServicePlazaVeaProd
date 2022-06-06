package com.plazavea.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "admin_seq")
    @GenericGenerator(name = "admin_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "USR_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idAdmin;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column
    private int dni;
    @Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column
    private int numTelefonico;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "admin")
    private List<Tienda> tienda;

    @OneToMany(mappedBy = "admin")
    private List<Pedido> pedidos;
}
