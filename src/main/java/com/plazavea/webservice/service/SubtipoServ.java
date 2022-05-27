package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Subtipo;

public interface SubtipoServ {
    public void registrar(Subtipo subtipo);
    public void editar(Subtipo subtipo);
    public void eliminar(int id);
    public List<Subtipo> listar();
    public Subtipo buscar(int id);
}
