package com.plazavea.webservice.controller;

import java.time.LocalDate;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plazavea.webservice.dto.ProductoTiendaReq;
import com.plazavea.webservice.dto.ProductoTiendaRes;
import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.model.ProductoTienda;
import com.plazavea.webservice.model.Tienda;
import com.plazavea.webservice.service.ProductoServ;
import com.plazavea.webservice.service.ProductoTiendaServ;
import com.plazavea.webservice.service.TiendaServ;

@RestController
@RequestMapping("/productotienda")
public class ProductoTiendaController {

    @Autowired
    private ProductoTiendaServ service;

    @Autowired
    private TiendaServ tiendaServ;

    @Autowired
    private ProductoServ productoServ;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("tienda/{idTienda}")
    public ResponseEntity<?> getByTienda(@PathVariable String idTienda, Pageable page,
            @RequestParam(required = false, defaultValue = "false") boolean unpaged) {
        if (unpaged) {
            page = PageRequest.of(0, Integer.MAX_VALUE);
        }
        Page<ProductoTiendaRes> items = service.listarProdByTienda(page, idTienda).map(
                new Function<ProductoTienda, ProductoTiendaRes>() {
                    public ProductoTiendaRes apply(ProductoTienda t) {
                        return mapper.map(t, ProductoTiendaRes.class);
                    }
                });
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("producto/{idProducto}")
    public ResponseEntity<?> getByProducto(@PathVariable String idProducto, Pageable page) {
        Page<ProductoTiendaRes> items = service.listarTiendaByProd(page, idProducto).map(
                new Function<ProductoTienda, ProductoTiendaRes>() {
                    public ProductoTiendaRes apply(ProductoTienda t) {
                        return mapper.map(t, ProductoTiendaRes.class);
                    }
                });
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{idTienda}/{idProducto}")
    public ResponseEntity<?> get(@PathVariable String idTienda, @PathVariable String idProducto) {
        ProductoTiendaRes item = mapper.map(
                service.buscar(idTienda, idProducto),
                ProductoTiendaRes.class);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> post(@RequestBody ProductoTiendaReq request) {
        Producto producto = productoServ.buscar(request.getIdProducto());
        Tienda tienda = tiendaServ.buscar(request.getIdTienda());
        if (producto == null || tienda == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (service.existe(request.getIdTienda(), request.getIdProducto())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        ProductoTienda item = mapper.map(request, ProductoTienda.class);
        item.setProducto(producto);
        item.setTienda(tienda);
        item.setFechaActualizacion(LocalDate.now());
        try {
            service.registrar(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/stock/empleado")
    public ResponseEntity<?> putStockEmp(@RequestBody ProductoTiendaReq request,
            @RequestParam(required = false, defaultValue = "true") boolean add) {
        ProductoTienda item = service.buscar(request.getIdTienda(), request.getIdProducto());
        if (item != null) {
            try {
                var stockActual = item.getStock();
                var stockEmpleado = request.getStock();
                if (add) {
                    item.setStock(stockActual + stockEmpleado);
                } else {
                    if (stockEmpleado > stockActual || stockActual == 0) {
                        throw new Exception("No hay stock Suficiente");
                    }
                    item.setStock(stockActual - stockEmpleado);
                }
                item.setFechaActualizacion(LocalDate.now());
                service.editar(item);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/stock/cliente")
    public ResponseEntity<?> putStockCli(@RequestBody ProductoTiendaReq request) {
        ProductoTienda item = service.buscar(request.getIdTienda(), request.getIdProducto());
        if (item != null) {
            try {
                var stockActual = item.getStock();
                var stockCliente = request.getStock();
                if (stockCliente > stockActual || stockActual == 0) {
                    throw new Exception("No hay Stock Suficiente");
                }
                item.setStock(stockActual - request.getStock());
                item.setFechaActualizacion(LocalDate.now());
                service.editar(item);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
