package tcc.utfpr.edu.br.soec.dto;

import java.util.Date;

import tcc.utfpr.edu.br.soec.Enum.EStatusCurriculo;

/**
 * Created by rodri on 15/06/2017.
 */

public class AvaliacaoCurriculoDTO {

    private Long id;
    private OportunidadeEmpregoDTO oportunidadeEmprego;
    //private Curriculo curriculo;
    private EStatusCurriculo status;
    private Boolean favorito;
    private Boolean isAceitaEntrevista;
    private Date dataCurriculoEnviado;
    private Date dataEntrevista;
    private Date dataEncerramento;
}
