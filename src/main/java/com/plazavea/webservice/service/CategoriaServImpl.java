package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Categoria;
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
        if (categoria.getSubcategorias().size()>0) {
            categoria.getSubcategorias().forEach(x->x.saveChilds());
        }
        
        repository.save(categoria);
    }

    @Override
    @Transactional
    public void registrarLista(List<Categoria> categorias){
        for (var c : categorias) { c.saveChilds();
            for (var sc : c.getSubcategorias()) { sc.saveChilds();
                for (var t : sc.getTipos()) { t.saveChilds();
                    for (var st : t.getSubtipos()) { st.saveChilds();
                        for (var p : st.getProductos()) { p.saveChilds();
        }}}}}

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
