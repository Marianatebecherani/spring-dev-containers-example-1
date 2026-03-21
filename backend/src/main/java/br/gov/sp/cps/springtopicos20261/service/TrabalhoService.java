package br.gov.sp.cps.springtopicos20261.service;

import java.util.List;

import br.gov.sp.cps.springtopicos20261.entity.Trabalho;

public interface TrabalhoService {

    public List<Trabalho> listar();

    public Trabalho cadastrar(Trabalho trabalho);

    public List<Trabalho> buscarPorTituloEAutor(String titulo, String nomeAutor);
    
}
