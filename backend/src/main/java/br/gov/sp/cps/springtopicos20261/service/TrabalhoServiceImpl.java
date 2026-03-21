package br.gov.sp.cps.springtopicos20261.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springtopicos20261.entity.Trabalho;
import br.gov.sp.cps.springtopicos20261.repository.TrabalhoRepository;

@Service
public class TrabalhoServiceImpl implements TrabalhoService {

    private TrabalhoRepository trabalhoRepo;

    private UsuarioService usuarioService;

    public TrabalhoServiceImpl(TrabalhoRepository trabalhoRepo, UsuarioService usuarioService) {
        this.trabalhoRepo = trabalhoRepo;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Trabalho> listar() {
        return trabalhoRepo.findAll();
    }

    @Override
    public Trabalho cadastrar(Trabalho trabalho) {
        if(trabalho.getTitulo() == null 
                || trabalho.getTitulo().isBlank()
                || trabalho.getAutor() == null
                || trabalho.getAutor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O título do trabalho e o autor são obrigatórios.");
        }
        if(trabalho.getDataHoraEntrega() == null) {
            trabalho.setDataHoraEntrega(LocalDateTime.now());
        }
        trabalho.setAutor(usuarioService.buscarPorId(trabalho.getAutor().getId()));
        return trabalhoRepo.save(trabalho); 
    }

    @Override
    public List<Trabalho> buscarPorTituloEAutor(String titulo, String nomeAutor) {
        if(titulo == null 
                || titulo.isBlank() 
                || nomeAutor == null 
                || nomeAutor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O título e o nome do autor são obrigatórios para a busca.");
        }
        return trabalhoRepo.findByTituloContainingIgnoreCaseAndAutorNomeContainingIgnoreCase(titulo, nomeAutor);
    }
    
}
