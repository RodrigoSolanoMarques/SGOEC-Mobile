package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tcc.utfpr.edu.br.soec.dto.CidadeDTO;


public interface CidadeService {

    @GET("cidade/listar")
    Call<List<CidadeDTO>> listar();

}
