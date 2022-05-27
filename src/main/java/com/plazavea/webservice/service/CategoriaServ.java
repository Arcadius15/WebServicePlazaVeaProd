package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Categoria;

public interface CategoriaServ {
    public void registrar(Categoria categoria);
    public void editar(Categoria categoria);
    public void eliminar(int id);
    public List<Categoria> listar();
    public Categoria buscar(int id);
}
