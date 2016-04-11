package br.com.waypoints.entity;

import java.util.Date;

public class Rota {

    private Integer id;
    private String nome;
    private Date dataInicio;
    private Date dataAtualizacao;
    private String status;
    private Integer idEntregador;
    private Integer idPlanejador;
    private Integer idGrupo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Integer idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Integer getIdPlanejador() {
        return idPlanejador;
    }

    public void setIdPlanejador(Integer idPlanejador) {
        this.idPlanejador = idPlanejador;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}
