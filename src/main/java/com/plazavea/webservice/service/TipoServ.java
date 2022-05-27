package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tipo;

public interface TipoServ {
    public void registrar(Tipo tipo);
    public void editar(Tipo tipo);
    public void eliminar(int id);
    public List<Tipo> listar();
    public Tipo buscar(int id);
}
