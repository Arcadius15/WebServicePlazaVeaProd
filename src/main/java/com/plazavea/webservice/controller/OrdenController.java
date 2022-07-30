package com.plazavea.webservice.controller;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.plazavea.webservice.dto.OrdenReq;
import com.plazavea.webservice.dto.OrdenRes;
import com.plazavea.webservice.model.Orden;
import com.plazavea.webservice.service.OrdenServ;
import com.plazavea.webservice.utils.PatchClass;

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
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping("/listar/{idCliente}")
    public ResponseEntity<Page<OrdenRes>> getAll(@PathVariable String idCliente, Pageable page) {
        try {
            Page<OrdenRes> items = repository.listar(idCliente,page).map(new Function<Orden,OrdenRes>() {
                @Override
                public OrdenRes apply(Orden t) {
                    return mapper.map(t, OrdenRes.class);
                }
            });

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdenRes> getById(@PathVariable("id") String id) {
        OrdenRes item = mapper.map(repository.buscar(id), OrdenRes.class) ;

        if (item!=null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrdenReq item) {
        try {
            Orden orden = mapper.map(item, Orden.class);
            var res = repository.registrar(orden);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Orden existingItem = repository.buscar(id);
        if (existingItem!=null) {
            Orden modifiedItem = (Orden) patchClass.patch(Orden.class, item, existingItem);
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
}
