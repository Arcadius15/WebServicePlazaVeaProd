package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Repartidor;

public interface RepartidorServ {
    public void editar(Repartidor item);
    public Page<Repartidor> listarPorTienda(String idTienda,Pageable page);
    public List<Repartidor> buscarPorTurno(int turno,String idTienda);
    public Repartidor buscar(String id);

}
