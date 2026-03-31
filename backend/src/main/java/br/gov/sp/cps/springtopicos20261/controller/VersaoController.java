package br.gov.sp.cps.springtopicos20261.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.cps.springtopicos20261.entity.Versao;
import br.gov.sp.cps.springtopicos20261.service.VersaoService;

@RestController
@CrossOrigin
@RequestMapping("/versao")
public class VersaoController {

    private VersaoService service;

    public VersaoController(VersaoService service) {
        this.service = service;
    }

    // Rota para listar todos os registros
    @GetMapping
    public ResponseEntity<List<Versao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Rota para cadastro
    @PostMapping
    public ResponseEntity<Versao> cadastrar(@RequestBody Versao versao) {
        Versao versaoCadastrada = service.cadastrar(versao);
        return ResponseEntity.created(URI.create("/versao/" + versaoCadastrada.getId())).body(versaoCadastrada);
    }

    // Rota para busca (Item 2 e 4 da prova)
    @GetMapping("/busca")
    public ResponseEntity<List<Versao>> buscarPorDataEPalavra(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
            @RequestParam("palavra") String palavra) {
        return ResponseEntity.ok(service.buscarPorDataEAnotacao(data, palavra));
    }
}
