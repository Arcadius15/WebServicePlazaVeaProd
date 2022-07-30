package com.plazavea.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.plazavea.webservice.dto.DireccionReq;
import com.plazavea.webservice.dto.DireccionRes;
import com.plazavea.webservice.model.Direccion;
import com.plazavea.webservice.service.DireccionServ;
import com.plazavea.webservice.utils.PatchClass;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<DireccionRes>> getAll(@PathVariable String id) {
        try {
            List<DireccionRes> items = repository.listar(id).stream().map(new Function<Direccion,DireccionRes>(){
                @Override
                public DireccionRes apply(Direccion t) {
                    return mapper.map(t, DireccionRes.class);
                }
            }).collect(Collectors.toList());

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DireccionRes> getById(@PathVariable("id") int id) {
        DireccionRes item = mapper.map(repository.buscar(id), DireccionRes.class) ;

        if (item!=null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DireccionReq item) {
        try {
            repository.guardar(mapper.map(item, Direccion.class));
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Direccion existingItem = repository.buscar(id);
        if (existingItem!=null) {
        	Direccion modifiedItem = (Direccion) patchClass.patch(Direccion.class, item, existingItem);
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
