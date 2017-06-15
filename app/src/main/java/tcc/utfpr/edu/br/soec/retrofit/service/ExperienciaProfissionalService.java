package tcc.utfpr.edu.br.soec.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;


public interface ExperienciaProfissionalService {

    @POST("experienciaProfissional/salvar")
    Call<List<ExperienciaProfissional>> salvar(@Body List<ExperienciaProfissional> experienciasProfissionais);

}
