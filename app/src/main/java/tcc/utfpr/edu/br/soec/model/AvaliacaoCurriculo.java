package tcc.utfpr.edu.br.soec.model;

import java.io.Serializable;
import java.util.Date;

import br.com.rafael.jpdroid.annotations.Column;
import br.com.rafael.jpdroid.annotations.Entity;
import br.com.rafael.jpdroid.annotations.ForeignKey;
import br.com.rafael.jpdroid.annotations.PrimaryKey;
import br.com.rafael.jpdroid.annotations.RelationClass;
import br.com.rafael.jpdroid.enums.RelationType;
import tcc.utfpr.edu.br.soec.Enum.EStatusCurriculo;

@Entity
public class AvaliacaoCurriculo  implements Serializable{

    @PrimaryKey
    @Column
    private Long _id;

    @Column
    private Long id;

    @Column(nullable = false)
    private EStatusCurriculo status;

    @Column(nullable = false)
    private Boolean favorito;

    @Column(nullable = true)
    private Boolean isAceitaEntrevista;

    //@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(nullable = false)
    private Date dataCurriculoEnviado;

    //@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(nullable = true)
    private Date dataEntrevista;

    //@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(nullable = true)
    private Date dataEncerramento;

    @ForeignKey(joinEntity = OportunidadeEmprego.class, joinPrimaryKey = "_id")
    @Column
    private Long idOportunidadeEmprego;

    @RelationClass(relationType = RelationType.OneToOne, joinColumn = "idOportunidadeEmprego")
    private OportunidadeEmprego oportunidadeEmprego;

    @ForeignKey(joinEntity = Curriculo.class, joinPrimaryKey = "_id")
    @Column
    private Long idCurriculo;

    @RelationClass(relationType = RelationType.OneToOne, joinColumn = "idCurriculo")
    private Curriculo curriculo;

    public AvaliacaoCurriculo() {
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

    public EStatusCurriculo getStatus() {
        return status;
    }

    public void setStatus(EStatusCurriculo status) {
        this.status = status;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public Boolean getAceitaEntrevista() {
        return isAceitaEntrevista;
    }

    public void setAceitaEntrevista(Boolean aceitaEntrevista) {
        isAceitaEntrevista = aceitaEntrevista;
    }

    public Date getDataCurriculoEnviado() {
        return dataCurriculoEnviado;
    }

    public void setDataCurriculoEnviado(Date dataCurriculoEnviado) {
        this.dataCurriculoEnviado = dataCurriculoEnviado;
    }

    public Date getDataEntrevista() {
        return dataEntrevista;
    }

    public void setDataEntrevista(Date dataEntrevista) {
        this.dataEntrevista = dataEntrevista;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Long getIdOportunidadeEmprego() {
        return idOportunidadeEmprego;
    }

    public void setIdOportunidadeEmprego(Long idOportunidadeEmprego) {
        this.idOportunidadeEmprego = idOportunidadeEmprego;
    }

    public OportunidadeEmprego getOportunidadeEmprego() {
        return oportunidadeEmprego;
    }

    public void setOportunidadeEmprego(OportunidadeEmprego oportunidadeEmprego) {
        this.oportunidadeEmprego = oportunidadeEmprego;
    }

    public Long getIdCurriculo() {
        return idCurriculo;
    }

    public void setIdCurriculo(Long idCurriculo) {
        this.idCurriculo = idCurriculo;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}
