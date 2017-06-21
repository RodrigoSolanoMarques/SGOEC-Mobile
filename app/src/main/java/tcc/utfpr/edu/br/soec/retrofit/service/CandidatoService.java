package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Pessoa;


public interface CandidatoService {

    @POST("candidato/salvar")
    Call<List<Candidato>> salvar(@Body List<Candidato> candidatos);

}
