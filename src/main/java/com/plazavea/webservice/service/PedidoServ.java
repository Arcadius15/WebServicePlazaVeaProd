package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Pedido;

public interface PedidoServ {
    
    public void registrar(Pedido pedido);
    public void editar(Pedido pedido);
    public void eliminar(String id);
    public List<Pedido> listar();
    public Pedido buscar(String id);
}
