package tcc.utfpr.edu.br.soec.model;

import java.io.Serializable;
import java.util.List;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.enums.RelationType;

@Entity
public class Curriculo implements Serializable {

	@PrimaryKey
	@Column
	private Long _id;

	@Column
	private Long id;

    @ForeignKey(joinEntity = Candidato.class, joinPrimaryKey = "_id", deleteCascade = true)
    @Column
    private Long idCandidato;

	@Column
	private String objetivoAreaAtuar;

	@Column
	private String conhecimentosGerais;

	@Column
	private String conhecimentosInformatica;

	/* Relacionamentos */

    @RelationClass(relationType = RelationType.OneToMany, joinColumn = "idCandidato")
	private Candidato candidato;

    @RelationClass(relationType=RelationType.ManyToMany,joinTable="CurriculoFormacoes")
	private List<Formacao> formacoes;

    @RelationClass(relationType=RelationType.ManyToMany,joinTable="CurriculoCursoComplementar")
	private List<CursoComplementar> cursoComplementares;

    @RelationClass(relationType=RelationType.ManyToMany,joinTable="CurriculoEsperienciaProfissional")
	private List<ExperienciaProfissional> experienciasProfissionais;




























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

    public Long getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getObjetivoAreaAtuar() {
        return objetivoAreaAtuar;
    }

    public void setObjetivoAreaAtuar(String objetivoAreaAtuar) {
        this.objetivoAreaAtuar = objetivoAreaAtuar;
    }

    public String getConhecimentosGerais() {
        return conhecimentosGerais;
    }

    public void setConhecimentosGerais(String conhecimentosGerais) {
        this.conhecimentosGerais = conhecimentosGerais;
    }

    public String getConhecimentosInformatica() {
        return conhecimentosInformatica;
    }

    public void setConhecimentosInformatica(String conhecimentosInformatica) {
        this.conhecimentosInformatica = conhecimentosInformatica;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    public List<CursoComplementar> getCursoComplementares() {
        return cursoComplementares;
    }

    public void setCursoComplementares(List<CursoComplementar> cursoComplementares) {
        this.cursoComplementares = cursoComplementares;
    }

    public List<ExperienciaProfissional> getExperienciasProfissionais() {
        return experienciasProfissionais;
    }

    public void setExperienciasProfissionais(List<ExperienciaProfissional> experienciasProfissionais) {
        this.experienciasProfissionais = experienciasProfissionais;
    }
}
