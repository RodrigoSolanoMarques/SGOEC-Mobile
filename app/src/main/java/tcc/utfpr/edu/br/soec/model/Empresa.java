package tcc.utfpr.edu.br.soec.model;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.enums.RelationType;

public class Empresa {

	@PrimaryKey
	@Column
	private Long _id;

	@Column
	private Long id;

	@ForeignKey(joinEntity = Cidade.class, joinPrimaryKey = "_id")
	@Column
	private Long idCidade;

	@Column
	private String razaoSocial;

	@Column
	private String nomeFantasia;

	@Column
	private String missao;

	@Column
	private String visao;

	@Column
	private String valores;

	@Column
	private Boolean isPessoaJuridica;

	@Column
	private String cpfCnpj;

	@Column
	private String inscricaoEstadual;

	@RelationClass(relationType = RelationType.OneToMany, joinColumn = "idCidade")
	private Cidade cidade;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
