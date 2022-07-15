package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tarjeta;

public interface TarjetaServ {
    public void registrar(Tarjeta tarjeta);
    public void editar(Tarjeta tarjeta);
    public void eliminar(int id);
    public List<Tarjeta> listar(String idCliente);
    public Tarjeta buscar(int id);
    
}
