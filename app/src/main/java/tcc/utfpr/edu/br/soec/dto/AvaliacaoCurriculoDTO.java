package tcc.utfpr.edu.br.soec.dto;

import java.io.Serializable;
import java.util.Date;


import tcc.utfpr.edu.br.soec.Enum.EStatusCurriculo;

/**
 * Created by rodri on 15/06/2017.
 */

public class AvaliacaoCurriculoDTO implements Serializable {

    private Long id;
    private OportunidadeEmpregoDTO oportunidadeEmprego;
    private EStatusCurriculo status;
    private Boolean favorito;
    private Boolean isAceitaEntrevista;
    private Date dataCurriculoEnviado;
    private Date dataEntrevista;
    private Date dataEncerramento;
    private Long idCurriculo;

    public Long getId() {
        return id;
    }

    public OportunidadeEmpregoDTO getOportunidadeEmprego() {
        return oportunidadeEmprego;
    }

    public EStatusCurriculo getStatus() {
        return status;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public Boolean getAceitaEntrevista() {
        return isAceitaEntrevista;
    }

    public Date getDataCurriculoEnviado() {
        return dataCurriculoEnviado;
    }

    public Date getDataEntrevista() {
        return dataEntrevista;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public Long getIdCurriculo() {
        return idCurriculo;
    }
}
