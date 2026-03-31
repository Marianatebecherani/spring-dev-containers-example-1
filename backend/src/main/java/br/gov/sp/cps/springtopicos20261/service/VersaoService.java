package br.gov.sp.cps.springtopicos20261.service;

import java.time.LocalDate;
import java.util.List;
import br.gov.sp.cps.springtopicos20261.entity.Versao;

public interface VersaoService {

    public Versao cadastrar(Versao versao);

    public List<Versao> listarTodos();

    public List<Versao> buscarPorDataEAnotacao(LocalDate data, String palavra);
}