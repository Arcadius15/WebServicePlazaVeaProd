package com.plazavea.webservice.controller;


import com.plazavea.webservice.dto.ProductoReq;
import com.plazavea.webservice.dto.ProductoRes;
import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.service.ProductoServ;
import com.plazavea.webservice.service.SubCategoriaServ;
import com.plazavea.webservice.utils.PatchClass;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoServ repository;

    @Autowired
    private SubCategoriaServ repositorySc;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping
    public ResponseEntity<Page<ProductoRes>> getAll(Pageable page) {
        try {
            Page<ProductoRes> content = repository.listar(page).map(new Function<Producto,ProductoRes>(){
                @Override
                public ProductoRes apply(Producto t) {
                    return mapper.map(t, ProductoRes.class);
                }
            });

            if (content.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductoRes> getById(@PathVariable("id") String id) {
        Producto item = repository.buscar(id);
        if (item!=null) {
            return new ResponseEntity<>(mapper.map(item, ProductoRes.class) , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductoReq item) {
        try {
            repository.registrar(mapper.map(item, Producto.class) );
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Producto existingItem = repository.buscar(id);
        if (existingItem!=null) {
            Producto modifiedItem = (Producto) patchClass.patch(Producto.class, item, existingItem);
            if (modifiedItem==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            repository.editar(existingItem);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            repository.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("subcategoria/{id}")
    public ResponseEntity<Page<ProductoRes>> getProductosBySubCat(@PathVariable int id,Pageable page,@RequestParam(defaultValue = "false") boolean unpaged) {
        try {
            SubCategoria sc = repositorySc.buscar(id);
            if (sc==null) throw new Exception("No existe Sub Categoria");
            
            if (unpaged) {page = PageRequest.of(0, Integer.MAX_VALUE);}
            Page<ProductoRes> content = repository.listarPorSubCat(sc,page).map(new Function<Producto,ProductoRes>(){
                @Override
                public ProductoRes apply(Producto t) {
                    return mapper.map(t, ProductoRes.class);
                }
            });

            if (content.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
