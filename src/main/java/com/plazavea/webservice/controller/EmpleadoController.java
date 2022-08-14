package com.plazavea.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.plazavea.webservice.dto.EmpleadoRes;
import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.service.EmpleadoServ;
import com.plazavea.webservice.utils.PatchClass;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoServ repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping
    public ResponseEntity<Page<EmpleadoRes>> getAll(Pageable page) {
        try {
            List<EmpleadoRes> content = repository.listar(page)
                .stream()
                .map(x->
                    mapper.map(x, EmpleadoRes.class))
                .collect(Collectors.toList());

            if (content.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            Page<EmpleadoRes> items = new PageImpl<>(content);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Empleado> getById(@PathVariable("id") String id) {
        Empleado item = repository.buscar(id);

        if (item!=null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Map<@NotNull Object,@NotNull Object> item) {
        Empleado existingItem = repository.buscar(id);
        if (existingItem!=null) {
        	Empleado modifiedItem = (Empleado) patchClass.patch(Empleado.class, item, existingItem);
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

    @GetMapping("/admins")
    public ResponseEntity<?> getAdmins(){
        List<Empleado> listado = repository.buscarAdmins();
        if (listado!=null) {
            List<EmpleadoRes> listadoDto = listado.stream().map(new Function<Empleado,EmpleadoRes>() {
                @Override
                public EmpleadoRes apply(Empleado t) {
                    return mapper.map(t, EmpleadoRes.class);
                }
            }).collect(Collectors.toList());
            return new ResponseEntity<>(listadoDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
