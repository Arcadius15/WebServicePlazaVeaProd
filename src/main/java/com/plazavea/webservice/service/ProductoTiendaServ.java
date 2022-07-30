package com.plazavea.webservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plazavea.webservice.model.ProductoTienda;

public interface ProductoTiendaServ {
    public void registrar(ProductoTienda producto);
    public void editar(ProductoTienda producto);
    public Page<ProductoTienda> listarProdByTienda(Pageable page,String idTienda);
    public Page<ProductoTienda> listarTiendaByProd(Pageable page,String idProducto);
    public ProductoTienda buscar(String idTienda,String idProducto);
    public boolean existe(String idTienda,String idProducto);
}
