package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Usuario;

public interface UsuarioServ {
    public void registrar(Usuario usuario) throws Exception;
    public void editar(Usuario usuario);
    public void eliminar(int id);
    public List<Usuario> listar();
    public Usuario buscar(int id);
}
