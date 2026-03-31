package br.gov.sp.cps.springtopicos20261.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.gov.sp.cps.springtopicos20261.entity.Versao;
import br.gov.sp.cps.springtopicos20261.repository.VersaoRepository;
import br.gov.sp.cps.springtopicos20261.repository.AnotacaoRepository;

@Service
public class VersaoServiceImpl implements VersaoService {

    private VersaoRepository versaoRepo;
    private AnotacaoRepository anotacaoRepo;

    public VersaoServiceImpl(VersaoRepository versaoRepo, AnotacaoRepository anotacaoRepo) {
        this.versaoRepo = versaoRepo;
        this.anotacaoRepo = anotacaoRepo;
    }

    @Override
    public List<Versao> listarTodos() {
        return versaoRepo.findAll();
    }

    @Override
    public Versao cadastrar(Versao versao) {
        // Validação: Texto preenchido e sem números 
        if (versao.getNovoTexto() == null || versao.getNovoTexto().isBlank() || versao.getNovoTexto().matches(".*\\d.*")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto novo inválido ou contém números.");
        }
        
        // Validação: Nível de diferença entre 0 e 10 
        if (versao.getNivelDiferenca() != null && (versao.getNivelDiferenca() < 0 || versao.getNivelDiferenca() > 10)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nível de diferença deve estar entre 0 e 10.");
        }

        // Validação: Anotação associada existe 
        if (versao.getAnotacao() == null || versao.getAnotacao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anotação obrigatória.");
        }

        // Verificar se a anotação existe no banco de dados
        if (!anotacaoRepo.findById(versao.getAnotacao().getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anotação não encontrada no banco de dados.");
        }

        // Data atual se não informada [cite: 42]
        if (versao.getData() == null) {
            versao.setData(LocalDate.now());
        }

        return versaoRepo.save(versao);
    }

    @Override
    public List<Versao> buscarPorDataEAnotacao(LocalDate data, String palavra) {
        return versaoRepo.findByDataLessThanAndAnotacaoTextoContainingIgnoreCase(data, palavra);
    }
}
