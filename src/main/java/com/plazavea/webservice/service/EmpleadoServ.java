package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Empleado;


public interface EmpleadoServ {
    public void registrar(Empleado empleado);
    public void editar(Empleado empleado);
    public void eliminar(String id);
    public List<Empleado> listar();
    public Empleado buscar(String id);
}
