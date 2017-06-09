package tcc.utfpr.edu.br.soec.model;

import java.io.Serializable;
import java.util.Date;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.PrimaryKey;

@Entity
public class CursoComplementar  implements Serializable{

	@PrimaryKey
	@Column
	private long _id;

	@Column
	private Long id;

	@Column
	private Integer periodo;

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

	public CursoComplementar() {
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

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CursoComplementar that = (CursoComplementar) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (nomeCurso != null ? !nomeCurso.equals(that.nomeCurso) : that.nomeCurso != null)
			return false;
		if (instituicao != null ? !instituicao.equals(that.instituicao) : that.instituicao != null)
			return false;
		if (dataInicial != null ? !dataInicial.equals(that.dataInicial) : that.dataInicial != null)
			return false;
		return dataFinal != null ? dataFinal.equals(that.dataFinal) : that.dataFinal == null;

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
