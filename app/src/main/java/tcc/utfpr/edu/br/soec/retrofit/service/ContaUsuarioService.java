package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.dto.EstadoDTO;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;


public interface ContaUsuarioService {

    @POST("contaUsuario/salvar")
    Call<List<ContaUsuario>> salvar(@Body List<ContaUsuario> contasUsuarios);

}
