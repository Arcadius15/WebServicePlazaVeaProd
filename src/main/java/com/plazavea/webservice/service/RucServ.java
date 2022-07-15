package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Ruc;

public interface RucServ {
    public void registrar(Ruc ruc);
    public void editar(Ruc ruc);
    public void eliminar(int id);
    public List<Ruc> listar(String id);
    public Ruc buscar(int id);
}
