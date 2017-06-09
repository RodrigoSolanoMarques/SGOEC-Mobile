package tcc.utfpr.edu.br.soec.model;

import java.io.Serializable;
import java.util.Date;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.PrimaryKey;

@Entity
public class ExperienciaProfissional implements Serializable{

	@PrimaryKey
	@Column
	private long _id;

	@Column
	private Long id;

	@Column
	private String nomeEmpresa;

	@Column
	private String cargo;

	@Column
	private Boolean isAtual;

	@Column
	private Date dataInicial;

	@Column
	private Date dataFinal;

	@Column
	private String atividades;

	public ExperienciaProfissional() {
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

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getAtual() {
		return isAtual;
	}

	public void setAtual(Boolean atual) {
		isAtual = atual;
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

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExperienciaProfissional that = (ExperienciaProfissional) o;

		if (nomeEmpresa != null ? !nomeEmpresa.equals(that.nomeEmpresa) : that.nomeEmpresa != null)
			return false;
		if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;
		if (dataInicial != null ? !dataInicial.equals(that.dataInicial) : that.dataInicial != null)
			return false;
		return dataFinal != null ? dataFinal.equals(that.dataFinal) : that.dataFinal == null;

	}

	@Override
	public int hashCode() {
		int result = nomeEmpresa != null ? nomeEmpresa.hashCode() : 0;
		result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
		result = 31 * result + (dataInicial != null ? dataInicial.hashCode() : 0);
		result = 31 * result + (dataFinal != null ? dataFinal.hashCode() : 0);
		return result;
	}
}
