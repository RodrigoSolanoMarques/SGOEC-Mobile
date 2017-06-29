package tcc.utfpr.edu.br.soec.retrofit.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tcc.utfpr.edu.br.soec.dto.AvaliacaoCurriculoDTO;
import tcc.utfpr.edu.br.soec.model.AvaliacaoCurriculo;


public interface AvaliacaoCurriculoService {

    @GET("avaliacaoCurriculo/{idPessoa}/{idOportunidadeEmprego}")
    Call<AvaliacaoCurriculoDTO> findByPessoa(@Path("idPessoa") Long idPessoa,
                                                @Path("idOportunidadeEmprego") Long idOportunidadeEmprego);

    @POST("avaliacaoCurriculo/salvar/{idCurriculo}/{idOportunidadeEmprego}")
    Call<AvaliacaoCurriculo> salvar(@Path("idCurriculo") Long idCurriculo,
                                    @Path("idOportunidadeEmprego") Long idOportunidadeEmprego);
}
