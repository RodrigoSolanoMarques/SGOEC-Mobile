package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;


public interface CurriculoService {

    @POST("curriculo/salvar")
    Call<List<Curriculo>> salvar(@Body List<Curriculo> curriculos);

}
