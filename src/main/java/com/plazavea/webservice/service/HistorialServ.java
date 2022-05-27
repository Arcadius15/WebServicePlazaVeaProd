package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.HistorialOrden;

public interface HistorialServ {
    public void registrar(HistorialOrden historialOrden);
    public void editar(HistorialOrden historialOrden);
    public void eliminar(int id);
    public List<HistorialOrden> listar();
    public HistorialOrden buscar(int id);
}
