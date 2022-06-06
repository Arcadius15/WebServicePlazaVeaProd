package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.PedidoHistorial;

public interface PedidoHistorialServ {
    public void registrar(PedidoHistorial hpedido);
    public void editar(PedidoHistorial hpedido);
    public void eliminar(int id);
    public List<PedidoHistorial> listar();
    public PedidoHistorial buscar(int id);
}
