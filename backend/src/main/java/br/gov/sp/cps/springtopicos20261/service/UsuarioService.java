package br.gov.sp.cps.springtopicos20261.service;

import java.util.List;

import br.gov.sp.cps.springtopicos20261.entity.Usuario;

public interface UsuarioService {

    public Usuario cadastrar(Usuario usuario);

    public Usuario buscarPorId(Long id);

    public List<Usuario> buscarTodos();
    
}
