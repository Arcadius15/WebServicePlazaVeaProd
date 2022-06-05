package com.plazavea.webservice.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.plazavea.webservice.enums.Roles;

import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    @Enumerated(EnumType.STRING)
    private Roles rol;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}
