package br.gov.sp.cps.springtopicos20261.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aud_auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aud_id")
    private Long id;

    @Column(name = "aud_nome_antigo")
    private String nomeAntigo;

    @Column(name = "aud_nome_novo")
    private String nomeNovo;

    @Column(name = "aud_data_hora")
    private LocalDateTime dataHora;

    @Column(name = "aud_risco")
    private Float risco;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aud_usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "aud_autorizacao", nullable = false)
    private Autorizacao autorizacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public String getNomeNovo() {
        return nomeNovo;
    }

    public void setNomeNovo(String nomeNovo) {
        this.nomeNovo = nomeNovo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Float getRisco() {
        return risco;
    }

    public void setRisco(Float risco) {
        this.risco = risco;
    }

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

}
