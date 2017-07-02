package tcc.utfpr.edu.br.soec.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.Enum.EStatusCurriculo;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.dto.AvaliacaoCurriculoDTO;
import tcc.utfpr.edu.br.soec.dto.OportunidadeEmpregoDTO;
import tcc.utfpr.edu.br.soec.fragment.ListarCurriculoDialogFragment;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.retrofit.service.AvaliacaoCurriculoService;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

public class AvaliacaoCurriculoActivity extends FragmentActivity implements ListarCurriculoDialogFragment.FragmentListener {


    private OportunidadeEmpregoDTO oportunidadeEmpregoDTO;
    private RetrofitInicializador retrofitInicializador;
    private Jpdroid mDataBase;

    private EditText mEmpresa;
    private EditText mCargo;
    private EditText mEstado;
    private EditText mCidade;
    private EditText mSalario;
    private EditText mSituacao;
    private EditText mDataEntrevista;
    private TextInputLayout mTextInputLayoutSalario;
    private TextInputLayout mTextInputLayoutSituacao;
    private Button btnEnviarCurriculo;
    private AvaliacaoCurriculoDTO avaliacaoCurriculoDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_curriculo);

        getViews();
        setOnclickBtnEnviarCurriculo();

        mDataBase = Jpdroid.getInstance();
        mDataBase.open();

        Long idPessoa = null;
        List<Pessoa> pessoas = mDataBase.retrieve(Pessoa.class);
        if (!pessoas.isEmpty()) {
            idPessoa = pessoas.get(0).getId();
        }

        oportunidadeEmpregoDTO = getOportunidadeEmpregoIntent();

        mEmpresa.setText(oportunidadeEmpregoDTO.getCargo().getEmpresa().getNomeFantasia());
        mCargo.setText(oportunidadeEmpregoDTO.getCargo().getNome());
        mEstado.setText(oportunidadeEmpregoDTO.getCidade().getEstado().getNome());
        mCidade.setText(oportunidadeEmpregoDTO.getCidade().getNome());
        mSalario.setText(oportunidadeEmpregoDTO.getSalario());
        if (oportunidadeEmpregoDTO.getIsSalario() == null || !oportunidadeEmpregoDTO.getIsSalario()) {
            mTextInputLayoutSalario.setVisibility(View.GONE);
        }


        retrofitInicializador = new RetrofitInicializador(true);
        AvaliacaoCurriculoService avaliacaoCurriculoService = retrofitInicializador.getAvaliacaoCurriculoService();

        Call<AvaliacaoCurriculoDTO> callAvaliacaoCurriculo = avaliacaoCurriculoService.findByPessoa(idPessoa, oportunidadeEmpregoDTO.getId());

        callAvaliacaoCurriculo.enqueue(new Callback<AvaliacaoCurriculoDTO>() {
            @Override
            public void onResponse(Call<AvaliacaoCurriculoDTO> call, Response<AvaliacaoCurriculoDTO> response) {
                avaliacaoCurriculoDTO = response.body();

                if(avaliacaoCurriculoDTO != null){
                    mSituacao.setVisibility(View.VISIBLE);
                    mSituacao.setText(avaliacaoCurriculoDTO.getStatus().toString());
                    btnEnviarCurriculo.setVisibility(View.GONE);
                }

                if(EStatusCurriculo.ENTREVISTA_MARCADA == avaliacaoCurriculoDTO.getStatus()){
                    mDataEntrevista.setVisibility(View.VISIBLE);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    mDataEntrevista.setText(sdf.format(avaliacaoCurriculoDTO.getDataEntrevista()));
                }
            }

            @Override
            public void onFailure(Call<AvaliacaoCurriculoDTO> call, Throwable t) {
                if(avaliacaoCurriculoDTO != null){

                }
            }
        });
    }

    private void getViews() {
        mEmpresa = (EditText) findViewById(R.id.activity_avaliacao_curriculo_empresa);
        mCargo = (EditText) findViewById(R.id.activity_avaliacao_curriculo_cargo);
        mEstado = (EditText) findViewById(R.id.activity_avaliacao_curriculo_estado);
        mCidade = (EditText) findViewById(R.id.activity_avaliacao_curriculo_cidade);
        mSalario = (EditText) findViewById(R.id.activity_avaliacao_curriculo_salario);
        mSituacao = (EditText) findViewById(R.id.activity_avaliacao_curriculo_situacao);
        mDataEntrevista = (EditText) findViewById(R.id.activity_avaliacao_curriculo_data_entrevista);
        mTextInputLayoutSalario = (TextInputLayout) findViewById(R.id.textInputLayout5);
        mTextInputLayoutSituacao = (TextInputLayout) findViewById(R.id.textInputLayout6);
        btnEnviarCurriculo = (Button) findViewById(R.id.btnEnviarCurriculo);
    }

    private void setOnclickBtnEnviarCurriculo() {
        btnEnviarCurriculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListarCurriculoDialogFragment listarCurriculoDialogFragment = new ListarCurriculoDialogFragment(oportunidadeEmpregoDTO.getId());
                listarCurriculoDialogFragment.show(getFragmentManager(), "");
            }
        });
    }

    private OportunidadeEmpregoDTO getOportunidadeEmpregoIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras.containsKey("oportunidadeEmprego")) {
            return (OportunidadeEmpregoDTO) extras.getSerializable("oportunidadeEmprego");
        } else {
            ToastUtils.setMsgLong(getApplication(), "Não foi possível carregar informações.");
            finish();
            return null;

        }
    }

    @Override
    public void ListarCurriculoDialogFragmentOnclick() {
        finish();
    }
}
