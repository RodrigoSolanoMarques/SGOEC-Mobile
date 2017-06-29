package tcc.utfpr.edu.br.soec.Enum;

/**
 * Created by rodri on 15/06/2017.
 */

public enum EStatusCurriculo {

    APROVADO(0),
    DISPENSADO(1),
    AGUARDANDO_AVALIACAO(2),
    AVALIADO(3),
    LISTA_DE_FAVORITOS(4),
    LISTA_ESPERA(5),
    ENTREVISTA_MARCADA(6),
    SOLICITOU_CURRICULO(7);

    public Integer status;

    EStatusCurriculo(Integer status){
        this.status = status;
    };

    public Integer getValor(){
        return this.status;
    }
}
