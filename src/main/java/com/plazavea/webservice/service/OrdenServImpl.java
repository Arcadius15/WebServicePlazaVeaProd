package com.plazavea.webservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.plazavea.webservice.model.Direccion;
import com.plazavea.webservice.model.HistorialOrden;
import com.plazavea.webservice.model.Orden;
import com.plazavea.webservice.model.OrdenDetalle;
import com.plazavea.webservice.model.OrdenDetalleKey;
import com.plazavea.webservice.repository.DireccionRepository;
import com.plazavea.webservice.repository.OrdenRepository;
import com.plazavea.webservice.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenServImpl implements OrdenServ{

    @Autowired
    private OrdenRepository repository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    @Transactional
    public String registrar(Orden orden) {
        Set<OrdenDetalle> od = new HashSet<OrdenDetalle>();

        Direccion direccion = direccionRepository
            .findByCliente_IdClienteAndDirActivo(
                orden.getCliente().getIdCliente(), 
                true)
            .get().stream().findFirst().orElse(null);
        
        if (direccion==null) {
            return null;
        }

        orden.setLat(direccion.getLatitud());
        orden.setLng(direccion.getLongitud());

        orden.getOrdendetalle().forEach(x->{
            var odn = new OrdenDetalle();
            var odk =  new OrdenDetalleKey();
            odk.setIdOrden(orden.getIdOrden());
            odk.setIdProducto(x.getProducto().getIdProducto());
            odn.setId(odk);
            odn.setCantidad(x.getCantidad());
            odn.setPrecio(x.getPrecio());
            odn.setOrden(orden);
            odn.setProducto(productoRepository.findById(x.getProducto().getIdProducto()).get());
            od.add(odn);
        });

        List<HistorialOrden> ho = new ArrayList<>();
        HistorialOrden h = new HistorialOrden();
        h.setDescripcion("Solicitud de Pedido Procesado.");
        h.setEstado(0);
        h.setFechaEstado(LocalDateTime.now());
        h.setOrden(orden);
        ho.add(h);
        orden.setHistorial(ho);
        orden.setOrdendetalle(od);
        Orden ordenResponse =  repository.save(orden);
        return ordenResponse.getIdOrden();
    }

    @Override
    @Transactional
    public void editar(Orden orden) {
        
        repository.saveAndFlush(orden);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Orden> listar(String idCliente,Pageable page) {
        
        return repository.findByCliente_IdCliente(idCliente,page);
    }

    @Override
    @Transactional(readOnly = true)
    public Orden buscar(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Orden> listarPorTienda(String idTienda, Pageable page) {
        
        return repository.findByTienda_IdTienda(idTienda, page);
    }
    
}
