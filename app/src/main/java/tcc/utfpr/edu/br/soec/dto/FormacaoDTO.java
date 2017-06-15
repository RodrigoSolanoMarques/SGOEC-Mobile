package tcc.utfpr.edu.br.soec.dto;

import java.io.Serializable;
import java.util.Date;

import tcc.utfpr.edu.br.soec.model.Formacao;

/**
 * Created by rodri on 09/06/2017.
 */

public class FormacaoDTO implements Serializable {

    private Long id;
    private Integer periodo;
    private String nomeCurso;
    private String instituicao;
    private Boolean isConcluido;
    private Date dataInicial;
    private Date dataFinal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Boolean getConcluido() {
        return isConcluido;
    }

    public void setConcluido(Boolean concluido) {
        isConcluido = concluido;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }


    public FormacaoDTO converterFormacao(Formacao formacao) {

        FormacaoDTO formacaoDTO = new FormacaoDTO();

        formacaoDTO.setId(formacao.getId());
        formacaoDTO.setNomeCurso(formacao.getNomeCurso());
        formacaoDTO.setInstituicao(formacao.getInstituicao());
        formacaoDTO.setPeriodo(formacao.getPeriodo());
        formacaoDTO.setDataInicial(formacao.getDataInicial());
        formacaoDTO.setDataFinal(formacao.getDataFinal());
        formacaoDTO.setConcluido(formacao.getConcluido());

        return formacaoDTO;
    }
}
