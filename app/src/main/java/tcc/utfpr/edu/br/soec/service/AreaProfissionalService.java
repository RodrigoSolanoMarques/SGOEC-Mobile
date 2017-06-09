package tcc.utfpr.edu.br.soec.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;


public interface AreaProfissionalService {

    @GET("areaProfissional/listar")
    Call<List<AreaProfissionalDTO>> listar();

    @GET("areaProfissional/nome")
    Call<List<AreaProfissional>> findByNome(@Body AreaProfissional areaProfissional);
}
