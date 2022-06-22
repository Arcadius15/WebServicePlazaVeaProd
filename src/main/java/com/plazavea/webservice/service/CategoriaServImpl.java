package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.model.Descripcion;
import com.plazavea.webservice.model.Especificaciones;
import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.model.Promocion;
import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.model.Subtipo;
import com.plazavea.webservice.model.Tipo;
import com.plazavea.webservice.repository.CategoriaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServImpl implements CategoriaServ{

    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional
    public void registrar(Categoria categoria) {
        for (SubCategoria subCategoria : categoria.getSubcategorias()) {
            subCategoria.setCategoria(categoria);
            for (Tipo tipo : subCategoria.getTipos()) {
                tipo.setSubcategoria(subCategoria);
                for (Subtipo subtipo : tipo.getSubtipos()) {
                    subtipo.setTipo(tipo);
                    for (Producto producto : subtipo.getProductos()) {
                        producto.setSubtipo(subtipo);
                    }
                }
            }
        }
        repository.save(categoria);
    }

    @Override
    @Transactional
    public void registrarLista(List<Categoria> categorias){
        //seteando el id de producto es sus clases hijos
        for (Categoria categoria : categorias) {
            for (SubCategoria subCategoria : categoria.getSubcategorias()) {
                subCategoria.setCategoria(categoria);
                for (Tipo tipo : subCategoria.getTipos()) {
                    tipo.setSubcategoria(subCategoria);
                    for (Subtipo subtipo : tipo.getSubtipos()) {
                        subtipo.setTipo(tipo);
                        for (Producto producto : subtipo.getProductos()) {
                            producto.setSubtipo(subtipo);
                            for (Especificaciones esp : producto.getEspecificaciones()) {
                                esp.setProducto(producto);
                            }
                            for (Descripcion des : producto.getDescripciones()) {
                                des.setProducto(producto);
                            }
                            for (Promocion promocion : producto.getPromociones()) {
                                promocion.setProducto(producto);
                            }
                        }
                    }
                }
            }
        }

        repository.saveAll(categorias);
    }

    @Override
    @Transactional
    public void editar(Categoria categoria) {
        repository.saveAndFlush(categoria);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
