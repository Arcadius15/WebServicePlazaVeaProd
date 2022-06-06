package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Proveedor;

public interface ProveedorServ {
    public void registrar(Proveedor proveedor);
    public void editar(Proveedor proveedor);
    public void eliminar(String id);
    public List<Proveedor> listar();
    public Proveedor buscar(String id);
}
