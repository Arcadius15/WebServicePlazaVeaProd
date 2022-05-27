package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Orden;

public interface OrdenServ {
    public void registrar(Orden orden);
    public void editar(Orden orden);
    public void eliminar(int id);
    public List<Orden> listar();
    public Orden buscar(int id);
}
