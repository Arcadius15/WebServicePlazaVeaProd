package com.plazavea.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "usuario")
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cliente_seq")
    @GenericGenerator(name = "cliente_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "CLI_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idCliente;
    @Column(nullable = false,length = 30)
    private String nombre;
    @Column(nullable = false,length = 50)
    private String apellidos;
    @Column(nullable = false,length = 8)
    private String dni;
    @Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column
    @Pattern(regexp="(^$|[0-9]{9})")
    private String numTelefonico;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_usuario",nullable = false, referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Tarjeta> tarjetas;

    @OneToMany(mappedBy = "cliente",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Ruc> rucs;

    @OneToMany(mappedBy = "cliente",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Orden> ordenes;

    @OneToMany(mappedBy = "cliente",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Direccion> direcciones;

}
