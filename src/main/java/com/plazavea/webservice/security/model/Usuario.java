package com.plazavea.webservice.security.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.model.Repartidor;
import com.plazavea.webservice.utils.StringPrefixedSequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"enabled","username","authorities","accountNonExpired","credentialsNonExpired","accountNonLocked"})
public class Usuario implements UserDetails{

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
    @JsonIgnore
    private String password;
    @Column
    private boolean activo;
    @Column
    private LocalDate pswExp;
    @Column
    private boolean blocked;

    @OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties({"usuario"})
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties({"usuario"})
    private Repartidor repartidor;

    @OneToOne(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties({"usuario"})
    private Empleado empleado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="rol_usuario",
        joinColumns = @JoinColumn(name="id_usuario",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_usuario) references usuario(id_usuario)")),
        inverseJoinColumns = @JoinColumn(name="id_rol",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_rol) references rol(id_rol)")))
    private Set<Rol> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRol().name())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        Period period = Period.between(LocalDate.now(), pswExp);
        if (period.getDays()<0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }
    
}
