package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.SubCategoria;

public interface SubCategoriaServ {
    public void registrar(SubCategoria subCategoria);
    public void editar(SubCategoria subCategoria);
    public void eliminar(int id);
    public List<SubCategoria> listar();
    public SubCategoria buscar(int id);
}
