package tcc.utfpr.edu.br.soec.dto;


import java.io.Serializable;

/**
 * Created by rodri on 15/06/2017.
 */

public class EmpresaDTO implements Serializable {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String missao;
    private String visao;
    private String valores;
    private Boolean isPessoaJuridica;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private CidadeDTO cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getVisao() {
        return visao;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public Boolean getPessoaJuridica() {
        return isPessoaJuridica;
    }

    public void setPessoaJuridica(Boolean pessoaJuridica) {
        isPessoaJuridica = pessoaJuridica;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }
}
