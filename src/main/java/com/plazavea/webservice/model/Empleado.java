package com.plazavea.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "empleado_seq")
    @GenericGenerator(name = "empleado_seq",strategy = "com.plazavea.webservice.utils.StringPrefixedSequenceGenerator",parameters = {
        @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM,value = "1"),
        @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER,value = "USR_"),
        @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
    })
    private String idEmpleado;
    @Column
    @NotBlank(message = "Nombre no puede ir Vacio")
    private String nombres;
    @Column
    @NotBlank(message = "Apellido no puede ir Vacio")
    private String apellidos;
    @Column(nullable = false,length = 8)
    @Pattern(regexp="(^$|[0-9]{8})",message = "DNI invalido")
    private String dni;
    @Column
    @Past(message = "Fecha Invalida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column
    @Pattern(regexp="(^$|[0-9]{9})",message = "Numero Telefonico Invalido")
    private String numTelefonico;

    @OneToOne
    @JsonIgnoreProperties({"empleado"})
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    // @OneToMany(mappedBy = "empleado")
    // @JsonIgnoreProperties({"empleado"})
    // private List<Tienda> tiendas;

    @OneToMany(mappedBy = "empleado")
    @JsonIgnoreProperties({"empleado"})
    private List<Pedido> pedidos;

    @ManyToOne
    @JoinColumn(name = "id_tienda",foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tienda) references tienda(id_tienda)"))
    private Tienda tienda;
}
