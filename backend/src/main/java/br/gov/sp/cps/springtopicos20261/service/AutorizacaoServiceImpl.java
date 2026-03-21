package br.gov.sp.cps.springtopicos20261.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springtopicos20261.entity.Autorizacao;
import br.gov.sp.cps.springtopicos20261.repository.AutorizacaoRepository;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    private AutorizacaoRepository autorizacaoRepo;

    public AutorizacaoServiceImpl(AutorizacaoRepository autorizacaoRepo) {
        this.autorizacaoRepo = autorizacaoRepo;
    }

    @Override
    public Autorizacao buscarPorId(Long id) {
        return autorizacaoRepo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe autorização com esse id!");
        });
    }
    
}
