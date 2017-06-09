package tcc.utfpr.edu.br.soec.model;

import java.io.Serializable;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.enums.RelationType;

@Entity
public class Candidato implements Serializable{

	@PrimaryKey
	@Column
	private Long _id;

	@Column
	private Long id;

	@ForeignKey(joinEntity = Pessoa.class, joinPrimaryKey = "_id", deleteCascade = true)
	@Column
	private Long idPessoa;

	@ForeignKey(joinEntity = ContaUsuario.class, joinPrimaryKey = "_id", deleteCascade = true)
	@Column
	private Long idContaUsuario;

	@Column
	private String titulacao;

	@Column
	private String areaProfissional;

	/* Relacionametos */

	@RelationClass(relationType = RelationType.OneToOne, joinColumn = "idPessoa")
	private Pessoa pessoa;

	@RelationClass(relationType = RelationType.OneToOne, joinColumn = "idContaUsuario")
	private ContaUsuario contaUsuario;

	public Candidato() {
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

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getAreaProfissional() {
		return areaProfissional;
	}

	public void setAreaProfissional(String areaProfissional) {
		this.areaProfissional = areaProfissional;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ContaUsuario getContaUsuario() {
		return contaUsuario;
	}

	public void setContaUsuario(ContaUsuario contaUsuario) {
		this.contaUsuario = contaUsuario;
	}

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdContaUsuario() {
        return idContaUsuario;
    }

    public void setIdContaUsuario(Long idContaUsuario) {
        this.idContaUsuario = idContaUsuario;
    }
}
