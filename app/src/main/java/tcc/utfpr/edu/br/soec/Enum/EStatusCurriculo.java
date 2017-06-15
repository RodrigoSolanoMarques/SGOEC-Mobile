package tcc.utfpr.edu.br.soec.Enum;

/**
 * Created by rodri on 15/06/2017.
 */

public enum EStatusCurriculo {

    APROVADO(0),
    DISPENSADO(1),
    AGUARDANDOAVALIACAO(2),
    AVALIADO(3),
    LISTAFAVORITOS(4),
    LISTAESPERA(5),
    ENTREVISTAMARCADA(6),
    SOLICITOUCURRICULO(7);

    public Integer status;

    EStatusCurriculo(Integer status){
        this.status = status;
    };

    public Integer getValor(){
        return this.status;
    }
}
