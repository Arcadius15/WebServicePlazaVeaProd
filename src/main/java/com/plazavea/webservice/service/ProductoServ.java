package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Producto;

public interface ProductoServ {
    public void registrar(Producto producto);
    public void editar(Producto producto);
    public void eliminar(int id);
    public List<Producto> listar();
    public Producto buscar(int id);
}
