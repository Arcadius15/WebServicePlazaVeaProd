package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.model.Producto;
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
        //set categoria
        categoria.getSubcategorias().forEach(x->x.setCategoria(categoria));
        //set sub categoria
        categoria.getSubcategorias().forEach(x->
            x.getTipos().forEach(s->
                s.setSubcategoria(s.getSubcategoria())
                )
            );
        //set tipo
        categoria.getSubcategorias().forEach(x->
            x.getTipos().forEach(s->
                s.getSubtipos().forEach(t->
                    t.setTipo(t.getTipo()))
                )
            );
        //set producto
        categoria.getSubcategorias().forEach(x->
            x.getTipos().forEach(s->
                s.getSubtipos().forEach(t->
                    t.getProductos().forEach(z->
                        z.setSubtipo(z.getSubtipo())
                    ))
                )
            );
        repository.save(categoria);
    }

    @Override
    @Transactional
    public void registrarLista(List<Categoria> categorias){
        for (Categoria categoria : categorias) {
            //set sub categoria
            //categoria.getSubcategorias().forEach(x->x.setCategoria(categoria));
            //set tipo
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
