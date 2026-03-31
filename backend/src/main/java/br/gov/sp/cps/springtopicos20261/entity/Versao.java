package br.gov.sp.cps.springtopicos20261.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ver_versao")
public class Versao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ver_id")
    private Long id;

    @Column(name = "ver_novo_texto", nullable = false, length = 256)
    private String novoTexto;

    @Column(name = "ver_justificativa", length = 100)
    private String justificativa;

    @Column(name = "ver_data", nullable = false)
    private LocalDate data;

    @Column(name = "ver_nivel_diferenca")
    private Float nivelDiferenca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ver_anotacao")
    private Anotacao anotacao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovoTexto() {
        return novoTexto;
    }

    public void setNovoTexto(String novoTexto) {
        this.novoTexto = novoTexto;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Float getNivelDiferenca() {
        return nivelDiferenca;
    }

    public void setNivelDiferenca(Float nivelDiferenca) {
        this.nivelDiferenca = nivelDiferenca;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
}