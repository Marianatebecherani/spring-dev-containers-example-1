package br.gov.sp.cps.springtopicos20261.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.sp.cps.springtopicos20261.entity.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    @Query("SELECT a FROM Auditoria a " +
           "JOIN a.autorizacao aut " +
           "WHERE a.nomeAntigo LIKE %:nomeAntigoPart% " +
           "AND aut.nome = :nomeAutorizacaoExato")
    List<Auditoria> buscarPorNomeAntigoEPorNomeAutorizacao(
        @Param("nomeAntigoPart") String nomeAntigoPart, 
        @Param("nomeAutorizacaoExato") String nomeAutorizacaoExato
    );

}