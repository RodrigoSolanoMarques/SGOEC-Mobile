package tcc.utfpr.edu.br.soec.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.utfpr.edu.br.soec.service.AreaProfissionalService;

public class RetrofitInicializador {

    private final Retrofit retrofit;
    public RetrofitInicializador() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.4:8085/api/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    public AreaProfissionalService getAreaProfissionalService(){
        return retrofit.create(AreaProfissionalService.class);
    }
}
