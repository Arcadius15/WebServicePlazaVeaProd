package com.plazavea.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plazavea.webservice.dto.RepartidorRes;
import com.plazavea.webservice.enums.RepartidorStatus;
import com.plazavea.webservice.model.Repartidor;
import com.plazavea.webservice.service.RepartidorServ;
import com.plazavea.webservice.utils.PatchClass;

@RestController
@RequestMapping("/repartidor")
public class RepartidorController {

    @Autowired
    private RepartidorServ servicio;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatchClass patchClass;

    @GetMapping("/turno/{idTienda}/{sTurno}")
    public ResponseEntity<?> getByTurno(@PathVariable String idTienda,@PathVariable String sTurno) {
        int turno = 0;
        switch (sTurno.toLowerCase()) {
            case "mañana":turno = 0;break;
            case "tarde":turno = 1;break;
            case "noche":turno = 2;break;
            default:break;
        }
        List<Repartidor> repartidor = servicio.buscarPorTurno(turno,idTienda);
        if (repartidor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RepartidorRes> lstdto = repartidor.stream().map(new Function<Repartidor,RepartidorRes>() {
          @Override
          public RepartidorRes apply(Repartidor t) {
              return mapper.map(t, RepartidorRes.class);
          }  
        }).collect(Collectors.toList());
        return new ResponseEntity<>(lstdto, HttpStatus.OK);
    }

    @GetMapping("/tienda/{idTienda}")
    public ResponseEntity<?> getByTienda(@PathVariable String idTienda,
            Pageable page) {
        Page<RepartidorRes> lst = servicio.listarPorTienda(idTienda, page)
                .map(new Function<Repartidor, RepartidorRes>() {
                    @Override
                    public RepartidorRes apply(Repartidor t) {
                        return mapper.map(t, RepartidorRes.class);
                    }
                });
        if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

    @PutMapping("/status/{idRepartidor}")
    public ResponseEntity<?> putStatus(@PathVariable String idRepartidor, @RequestParam(required = true) String status) {
        Repartidor repartidor = servicio.buscar(idRepartidor);
        if (repartidor!=null) {
            try {
                RepartidorStatus rs = Enum.valueOf(RepartidorStatus.class, status);
                repartidor.setStatus(rs);
                servicio.editar(repartidor);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{idRepartidor}")
    public ResponseEntity<?> patchRepartidor(@PathVariable String idRepartidor,
            @RequestBody Map<@NotNull Object, @NotNull Object> item) {
            Repartidor existingItem = servicio.buscar(idRepartidor);
            if (existingItem!=null) {
                Repartidor modifiedItem = (Repartidor) patchClass.patch(Repartidor.class, item, existingItem);
                servicio.editar(modifiedItem);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
