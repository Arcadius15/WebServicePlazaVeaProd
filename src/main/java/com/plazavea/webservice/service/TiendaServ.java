package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tienda;

public interface TiendaServ {
    public void registrar(Tienda tienda);
    public void editar(Tienda tienda);
    public void eliminar(int id);
    public List<Tienda> listar();
    public Tienda buscar(int id);
}
