package br.gov.sp.cps.springtopicos20261.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springtopicos20261.entity.Auditoria;
import br.gov.sp.cps.springtopicos20261.entity.Autorizacao;
import br.gov.sp.cps.springtopicos20261.repository.AuditoriaRepository;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepo;
    private final AutorizacaoService autorizacaoService;

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepo, AutorizacaoService autorizacaoService) {
        this.auditoriaRepo = auditoriaRepo;
        this.autorizacaoService = autorizacaoService;
    }

    // 1. Serviço de Cadastro com Validações
    @Override
    public Auditoria novoAuditoria(Auditoria auditoria) {
        // Validação básica de autorização
        if (auditoria.getAutorizacao() == null || auditoria.getAutorizacao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autorização não informada!");
        }

        // Validação: Nome novo deve ser diferente do anterior
        if (auditoria.getNomeNovo().equals(auditoria.getNomeAntigo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve ser diferente do nome antigo!");
        }

        // Validação: Mais de 8 caracteres
        if (auditoria.getNomeNovo().length() <= 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve ter mais de 8 caracteres!");
        }

        // Validação: inicia com "ROLE_"
        if (!auditoria.getNomeNovo().startsWith("ROLE_")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve começar com 'ROLE_'!");
        }

        // Validação: Risco entre 0 e 10 (se informado)
        if (auditoria.getRisco() != null && (auditoria.getRisco() < 0 || auditoria.getRisco() > 10)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O risco deve ser um valor entre 0 e 10!");
        }

        // Preenchimento de Data/Hora atual se estiver em branco
        if (auditoria.getDataHora() == null) {
            auditoria.setDataHora(LocalDateTime.now());
        }

        // Busca a autorização para garantir que existe antes de salvar
        Autorizacao aut = autorizacaoService.buscarPorId(auditoria.getAutorizacao().getId());
        auditoria.setAutorizacao(aut);

        return auditoriaRepo.save(auditoria);
    }

    // 2. Serviço para Listar Todos
    @Override
    public List<Auditoria> buscarTodos() {
        return auditoriaRepo.findAll();
    }

    // 3. Serviço de Consulta Específica com Parâmetros
    @Override
    public List<Auditoria> buscarPorFiltro(String antigo, String autorizacao) {
        // Validação: Ambos os parâmetros devem ser informados
        if (antigo == null || antigo.isBlank() || autorizacao == null || autorizacao.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ambos os parâmetros (nome antigo e autorização) devem ser informados!");
        }
        return auditoriaRepo.buscarPorNomeAntigoEPorNomeAutorizacao(antigo, autorizacao);
    }
}