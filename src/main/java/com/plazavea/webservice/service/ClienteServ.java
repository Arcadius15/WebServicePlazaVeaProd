package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Cliente;

public interface ClienteServ {
    public void registrar(Cliente cliente);
    public void editar(Cliente cliente);
    public void eliminar(String id);
    public List<Cliente> listar();
    public Cliente buscar(String id);
}
