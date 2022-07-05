package com.plazavea.webservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Empleado;


public interface EmpleadoServ {
    public void editar(Empleado empleado);
    public void eliminar(String id);
    public Page<Empleado> listar(Pageable page);
    public Empleado buscar(String id);
}
