package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.dto.OportunidadeEmpregoDTO;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;


public interface OportunidadeEmpregoService {

    @GET("oportunidadeEmprego/listar")
    Call<List<OportunidadeEmpregoDTO>> listar();

    @GET("oportunidadeEmprego/listarOportunidadeEmpregoByAreaProfissional/{nome}")
    Call<List<OportunidadeEmpregoDTO>> listarOportunidadeEmpregoByAreaProfissional(@Path("nome") String areaProfissional);
}
