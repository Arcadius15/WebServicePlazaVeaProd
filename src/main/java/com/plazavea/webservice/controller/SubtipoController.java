package com.plazavea.webservice.controller;


import com.plazavea.webservice.dto.SubTipoReq;
import com.plazavea.webservice.dto.SubtipoRes;
import com.plazavea.webservice.model.Subtipo;
import com.plazavea.webservice.service.SubtipoServ;
import com.plazavea.webservice.utils.PatchClass;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subtipo")
public class SubtipoController {

    @Autowired
    private SubtipoServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping
    public ResponseEntity<Page<SubtipoRes>> getAll(Pageable page) {
        try {
            Page<SubtipoRes> content = repository.listar(page).map(new Function<Subtipo,SubtipoRes>() {
                @Override
                public SubtipoRes apply(Subtipo t) {
                    return mapper.map(t, SubtipoRes.class);
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
    public ResponseEntity<SubtipoRes> getById(@PathVariable("id") int id) {
        Subtipo item = repository.buscar(id) ;
        if (item!=null) {
            return new ResponseEntity<>(mapper.map(item, SubtipoRes.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SubTipoReq item) {
        try {
            repository.registrar(mapper.map(item, Subtipo.class) );
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Subtipo existingItem = repository.buscar(id);
        if (existingItem!=null) {
        	Subtipo modifiedItem = (Subtipo) patchClass.patch(Subtipo.class, item, existingItem);
            if (modifiedItem==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            repository.editar(existingItem);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            repository.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
