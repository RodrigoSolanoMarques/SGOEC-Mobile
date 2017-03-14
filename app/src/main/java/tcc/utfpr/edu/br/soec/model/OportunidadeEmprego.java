package tcc.utfpr.edu.br.soec.model;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.enums.RelationType;

public class OportunidadeEmprego {

	@PrimaryKey
	@Column
	private Long _id;

	@Column
	private Long id;

	@ForeignKey(joinEntity = Cidade.class, joinPrimaryKey = "_id")
	@Column
	private Long idCidade;

	@ForeignKey(joinEntity = Cargo.class, joinPrimaryKey = "_id", deleteCascade = true)
	@Column
	private Long idCargo;

	@Column
	private String descricao;

	@Column
	private Boolean isSalario;

	@Column
	private String salario;

	@Column
	private String cargaHoraria;

	@Column
	private String beneficios;

	@Column
	private Boolean isFinalizado;

	/* Relacionamentos */

	@RelationClass(relationType = RelationType.OneToMany, joinColumn = "idCidade")
	private Cidade cidade;

	@RelationClass(relationType = RelationType.OneToMany, joinColumn = "idCargo")
	private Cargo cargo;

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

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSalario() {
		return isSalario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public Boolean getFinalizado() {
		return isFinalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		isFinalizado = finalizado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setSalario(Boolean salario) {
		isSalario = salario;
	}
}
