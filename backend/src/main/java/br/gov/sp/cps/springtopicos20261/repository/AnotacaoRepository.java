package br.gov.sp.cps.springtopicos20261.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.cps.springtopicos20261.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
    
}
