package br.gov.sp.cps.springtopicos20261.service;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.sp.cps.springtopicos20261.entity.Auditoria;

public interface AuditoriaService {
    
    Auditoria novoAuditoria(Auditoria catraca);

    List<Auditoria> buscarTodos();

    List<Auditoria> buscarPorFiltro(String nomeAntigoPart, String nomeAutorizacaoExato);

}