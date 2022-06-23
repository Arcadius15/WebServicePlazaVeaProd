package com.plazavea.webservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Subtipo;

public interface SubtipoServ {
    public void registrar(Subtipo subtipo);
    public void editar(Subtipo subtipo);
    public void eliminar(int id);
    public Page<Subtipo> listar(Pageable page);
    public Subtipo buscar(int id);
}
