package com.plazavea.webservice.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.Producto;

public interface ProductoServ {
    public void registrar(Producto producto);
    public void editar(Producto producto);
    public void eliminar(String id);
    public Page<Producto> listar(Pageable page);
    public Producto buscar(String id);
}
