package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Direccion;

public interface DireccionServ {
        public Direccion guardar(Direccion dir);
        public void editar(Direccion dir);
        public void eliminar(int id);
        public List<Direccion> listar(String id);
        public Direccion buscar(int id);
        public void activar(String idCliente, int id);
    
    
}
