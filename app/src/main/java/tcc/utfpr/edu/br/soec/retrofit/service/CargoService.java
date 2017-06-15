package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tcc.utfpr.edu.br.soec.dto.CargoDTO;
import tcc.utfpr.edu.br.soec.dto.EmpresaDTO;

/**
 * Created by rodri on 15/06/2017.
 */

public interface CargoService {

    @GET("cargo/listar")
    Call<List<CargoDTO>> listar();
}
