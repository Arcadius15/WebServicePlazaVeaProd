package com.plazavea.webservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Orden;

public interface OrdenServ {
    public String registrar(Orden orden);
    public void editar(Orden orden);
    public void eliminar(String id);
    public Page<Orden> listar(String idCliente,Pageable page);
    public Orden buscar(String id);
}
