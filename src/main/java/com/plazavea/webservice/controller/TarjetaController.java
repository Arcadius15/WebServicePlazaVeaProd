package com.plazavea.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.plazavea.webservice.dto.TarjetaReq;
import com.plazavea.webservice.dto.TarjetaRes;
import com.plazavea.webservice.model.Tarjeta;
import com.plazavea.webservice.service.TarjetaServ;
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
@RequestMapping("/tarjeta")
public class TarjetaController {

    @Autowired
    private TarjetaServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<TarjetaRes>> getAll(@PathVariable String id) {
        try {
            List<TarjetaRes> items = repository.listar(id).stream().map(new Function<Tarjeta,TarjetaRes>(){
                @Override
                public TarjetaRes apply(Tarjeta t) {
                    return mapper.map(t, TarjetaRes.class);
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
    public ResponseEntity<TarjetaRes> getById(@PathVariable("id") int id) {
        TarjetaRes item = mapper.map(repository.buscar(id), TarjetaRes.class) ;

        if (item!=null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TarjetaReq item) {
        try {
            repository.registrar(mapper.map(item, Tarjeta.class));
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Tarjeta existingItem = repository.buscar(id);
        if (existingItem!=null) {
        	Tarjeta modifiedItem = (Tarjeta) patchClass.patch(Tarjeta.class, item, existingItem);
            if (modifiedItem==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            repository.editar(modifiedItem);
            return new ResponseEntity<>(HttpStatus.OK);
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
