package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.model.Pessoa;


public interface PessoaService {

    @POST("pessoa/salvar")
    Call<List<Pessoa>> salvar(@Body List<Pessoa> pessoas);

}
