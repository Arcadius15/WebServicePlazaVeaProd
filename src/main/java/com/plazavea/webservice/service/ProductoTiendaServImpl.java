package com.plazavea.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.ProductoTienda;
import com.plazavea.webservice.repository.ProductoTiendaRepository;

@Service
public class ProductoTiendaServImpl implements ProductoTiendaServ{

    @Autowired
    private ProductoTiendaRepository repository;

    @Override
    @Transactional(readOnly = false)
    public void registrar(ProductoTienda producto) {
        repository.save(producto);
    }

    @Override
    @Transactional(readOnly = false)
    public void editar(ProductoTienda producto) {
        repository.saveAndFlush(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoTienda buscar(String idTienda,String idProducto) {
        return repository.findByProducto_IdProductoAndTienda_IdTienda
            (idProducto, idTienda).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductoTienda> listarProdByTienda(Pageable page, String idTienda) {
        
        return repository.findByTienda_IdTienda(idTienda, page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductoTienda> listarTiendaByProd(Pageable page, String idProducto) {
        
        return repository.findByProducto_IdProducto(idProducto, page);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existe(String idTienda, String idProducto) {
        return repository.existsByProducto_IdProductoAndTienda_IdTienda(idProducto, idTienda);
    }
    
}
