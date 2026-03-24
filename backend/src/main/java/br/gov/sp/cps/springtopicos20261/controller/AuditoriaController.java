package br.gov.sp.cps.springtopicos20261.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import br.gov.sp.cps.springtopicos20261.entity.Auditoria;
import br.gov.sp.cps.springtopicos20261.service.AuditoriaService;

@RestController
@RequestMapping(value = "/auditoria")
@CrossOrigin
public class AuditoriaController {

    private AuditoriaService service;

    public AuditoriaController(AuditoriaService service) {
        this.service = service;
    }

    @PostMapping
    public Auditoria cadastrar(@RequestBody Auditoria auditoria) {
        return service.novoAuditoria(auditoria);
    }

    @GetMapping
    public List<Auditoria> listarTodos() {
        return service.buscarTodos();
    }

    @GetMapping(value = "/buscar/{nomeAntigo}/{autorizacao}")
    public List<Auditoria> buscarPorNomes(
            @PathVariable("antigo") String antigo, 
            @PathVariable("autorizacao") String autorizacao) {
        return service.buscarPorFiltro(antigo, autorizacao);
    }
}