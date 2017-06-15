package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tcc.utfpr.edu.br.soec.dto.EstadoDTO;


public interface EstadoService {

    @GET("estado/listar")
    Call<List<EstadoDTO>> listar();

}
