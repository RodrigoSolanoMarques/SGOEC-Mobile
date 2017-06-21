package tcc.utfpr.edu.br.soec.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.dto.AvaliacaoCurriculoDTO;
import tcc.utfpr.edu.br.soec.dto.OportunidadeEmpregoDTO;
import tcc.utfpr.edu.br.soec.model.OportunidadeEmprego;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.retrofit.service.AvaliacaoCurriculoService;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

public class AvaliacaoCurriculoActivity extends AppCompatActivity {


    private OportunidadeEmpregoDTO oportunidadeEmpregoDTO;
    private RetrofitInicializador retrofitInicializador;
    private Jpdroid mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_curriculo);

        mDataBase = Jpdroid.getInstance();
        mDataBase.open();

        Long idPessoa = null;
        List<Pessoa> pessoas = mDataBase.retrieve(Pessoa.class);
        if(!pessoas.isEmpty()){
            idPessoa = pessoas.get(0).getId();
        }

        oportunidadeEmpregoDTO = getOportunidadeEmpregoIntent();
        retrofitInicializador = new RetrofitInicializador();
        AvaliacaoCurriculoService avaliacaoCurriculoService = retrofitInicializador.getAvaliacaoCurriculoService();

        Call<AvaliacaoCurriculoDTO> callAvaliacaoCurriculo = avaliacaoCurriculoService.findByPessoa(idPessoa, oportunidadeEmpregoDTO.getId());
        callAvaliacaoCurriculo.enqueue(new Callback<AvaliacaoCurriculoDTO>() {
            @Override
            public void onResponse(Call<AvaliacaoCurriculoDTO> call, Response<AvaliacaoCurriculoDTO> response) {
                AvaliacaoCurriculoDTO body = response.body();
            }

            @Override
            public void onFailure(Call<AvaliacaoCurriculoDTO> call, Throwable t) {
                ToastUtils.setMsgLong(getApplication(), "Não foi possível carregar informações.");
                finish();
            }
        });

    }

    private OportunidadeEmpregoDTO getOportunidadeEmpregoIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras.containsKey("oportunidadeEmprego")){
            return (OportunidadeEmpregoDTO) extras.getSerializable("oportunidadeEmprego");
        }else{
            ToastUtils.setMsgLong(getApplication(), "Não foi possível carregar informações.");
            finish();
            return null;

        }
    }
}
