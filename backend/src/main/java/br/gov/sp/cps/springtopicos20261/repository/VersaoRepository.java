package br.gov.sp.cps.springtopicos20261.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.cps.springtopicos20261.entity.Versao;

public interface VersaoRepository extends JpaRepository<Versao, Long> {

    // "LessThan" para datas anteriores e navegação no objeto anotacao (texto) [cite: 39, 46]
    public List<Versao> findByDataLessThanAndAnotacaoTextoContainingIgnoreCase(LocalDate data, String palavra);
    
}