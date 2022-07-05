package com.plazavea.webservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Cliente;

public interface ClienteServ {
    public void editar(Cliente cliente);
    public void eliminar(String id);
    public Page<Cliente> listar(Pageable page);
    public Cliente buscar(String id);
}
