package tcc.utfpr.edu.br.soec.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.retrofit.service.AreaProfissionalService;
import tcc.utfpr.edu.br.soec.retrofit.service.CargoService;
import tcc.utfpr.edu.br.soec.retrofit.service.CidadeService;
import tcc.utfpr.edu.br.soec.retrofit.service.CurriculoService;
import tcc.utfpr.edu.br.soec.retrofit.service.CursoComplementarService;
import tcc.utfpr.edu.br.soec.retrofit.service.EmpresaService;
import tcc.utfpr.edu.br.soec.retrofit.service.EstadoService;
import tcc.utfpr.edu.br.soec.retrofit.service.ExperienciaProfissionalService;
import tcc.utfpr.edu.br.soec.retrofit.service.FormacaoService;
import tcc.utfpr.edu.br.soec.utils.Prefs;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder().baseUrl(Prefs.LINKWEBSERVICE).client(client).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public AreaProfissionalService getAreaProfissionalService() {
        return retrofit.create(AreaProfissionalService.class);
    }

    public CidadeService getCidadeService() {
        return retrofit.create(CidadeService.class);
    }

    public EstadoService getEstadoService() {
        return retrofit.create(EstadoService.class);
    }

    public FormacaoService getFormacaoService() {
        return retrofit.create(FormacaoService.class);
    }

    public EmpresaService getEmpresaService() {
        return retrofit.create(EmpresaService.class);
    }

    public CargoService getCargoService() {
        return retrofit.create(CargoService.class);
    }

    public CursoComplementarService getCursoComplementaresService() {
        return retrofit.create(CursoComplementarService.class);
    }

    public ExperienciaProfissionalService getExperienciaprofissionalService() {
        return retrofit.create(ExperienciaProfissionalService.class);
    }

    public CurriculoService getCurriculolService() {
        return retrofit.create(CurriculoService.class);
    }
}
