package com.plazavea.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.plazavea.webservice.dto.SubcategoriaReq;
import com.plazavea.webservice.dto.SubcategoriaRes;
import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.service.SubCategoriaServ;
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
@RequestMapping("/subcategoria")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping
    public ResponseEntity<List<SubcategoriaRes>> getAll() {
        try {
            List<SubcategoriaRes> items = repository.listar()
                .stream()
                .map(x-> 
                    mapper.map(x, SubcategoriaRes.class))
                .collect(Collectors.toList());

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SubcategoriaRes> getById(@PathVariable("id") int id) {
        SubCategoria item = repository.buscar(id);
        if (item!=null) {
            return new ResponseEntity<>(mapper.map(item, SubcategoriaRes.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SubcategoriaReq item) {
        try {
            repository.registrar(mapper.map(item, SubCategoria.class));
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        SubCategoria existingItem = repository.buscar(id);
        if (existingItem!=null) {
            SubCategoria modifiedItem = (SubCategoria) patchClass.patch(SubCategoria.class, item, existingItem);
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
