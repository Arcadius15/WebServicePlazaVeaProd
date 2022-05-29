package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Admin;


public interface AdminServ {
    public void registrar(Admin admin);
    public void editar(Admin admin);
    public void eliminar(int id);
    public List<Admin> listar();
    public Admin buscar(int id);
}