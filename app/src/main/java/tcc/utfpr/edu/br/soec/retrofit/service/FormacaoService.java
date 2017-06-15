package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.model.Formacao;


public interface FormacaoService {

    @POST("formacao/salvar")
    Call<List<Formacao>> salvar(@Body List<Formacao> formacoes);

}
