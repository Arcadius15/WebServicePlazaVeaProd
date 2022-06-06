package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Admin;
import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.model.Repartidor;
import com.plazavea.webservice.model.Usuario;
import com.plazavea.webservice.repository.UsuarioRepository;
import com.plazavea.webservice.utils.ModelMapperConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServImpl implements UsuarioServ{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapperConfig mapper;

    @Override
    @Transactional
    public void registrar(Usuario usuario) throws Exception{
        if(usuario.getCliente()!=null){
            Cliente c = new Cliente();
            c = mapper.modelMapper().map(usuario.getCliente(), Cliente.class);
            c.setUsuario(usuario);
            usuario.setCliente(c);
        }else if(usuario.getAdmin()!=null){
            Admin a = new Admin();
            a = mapper.modelMapper().map(usuario.getAdmin(), Admin.class);
            a.setUsuario(usuario);
            usuario.setAdmin(a);
        }else if(usuario.getRepartidor()!=null){
            Repartidor r = new Repartidor();
            r = mapper.modelMapper().map(usuario.getRepartidor(), Repartidor.class);
            r.setUsuario(usuario);
            usuario.setRepartidor(r);
        }else{
            throw new Exception("Se debe ingresar Cliente|Admin|Repartidor.");
        }

       
        repository.save(usuario);
        
    }

    @Override
    @Transactional
    public void editar(Usuario usuario) {
        repository.saveAndFlush(usuario);
        
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(String id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
