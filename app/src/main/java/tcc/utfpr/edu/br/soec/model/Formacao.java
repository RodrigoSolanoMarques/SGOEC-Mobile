package tcc.utfpr.edu.br.soec.model;

import java.util.Date;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.PrimaryKey;

@Entity
public class Formacao {

	@PrimaryKey
	@Column
	private Long _id;

	@Column
	private Long id;

	@Column
	private String nomeCurso;

	@Column
	private String instituicao;

	@Column
	private Boolean isConcluido;

	@Column
	private Date dataInicial;

	@Column
	private Date dataFinal;

	public Formacao() {
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Formacao formacao = (Formacao) o;

		if (id != null ? !id.equals(formacao.id) : formacao.id != null) return false;
		if (nomeCurso != null ? !nomeCurso.equals(formacao.nomeCurso) : formacao.nomeCurso != null)
			return false;
		if (instituicao != null ? !instituicao.equals(formacao.instituicao) : formacao.instituicao != null)
			return false;
		if (dataInicial != null ? !dataInicial.equals(formacao.dataInicial) : formacao.dataInicial != null)
			return false;
		return dataFinal != null ? dataFinal.equals(formacao.dataFinal) : formacao.dataFinal == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (nomeCurso != null ? nomeCurso.hashCode() : 0);
		result = 31 * result + (instituicao != null ? instituicao.hashCode() : 0);
		result = 31 * result + (dataInicial != null ? dataInicial.hashCode() : 0);
		result = 31 * result + (dataFinal != null ? dataFinal.hashCode() : 0);
		return result;
	}
}
