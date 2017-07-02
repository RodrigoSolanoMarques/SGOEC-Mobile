package tcc.utfpr.edu.br.soec.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.OportunidadeEmpregoAdapter;
import tcc.utfpr.edu.br.soec.dto.OportunidadeEmpregoDTO;
import tcc.utfpr.edu.br.soec.interfaces.RecyclerViewOnClickListenerHack;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.retrofit.service.OportunidadeEmpregoService;

public class OportunidadesEmpregoActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    // Banco de dados
    private Jpdroid jpdroid;

    private Toolbar toolbar;
    private Spinner mSpinnerFiltroAreaProfissional;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private Toolbar activity_empresas_favoritas_toolbar_spinner;

    // Listas
    private List<String> mNomeAreasProfissionais = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas_favoritas);

        mContext = getApplicationContext();

        getViewById();
        jpdroid = Jpdroid.getInstance();

        constructorToolbar();

        mSpinnerFiltroAreaProfissional.setVisibility(View.GONE);
        activity_empresas_favoritas_toolbar_spinner.setVisibility(View.GONE);

        constructorRecycleView();
        enqueueOportunidadeEmpregoService(getOportunidadeEmpregoService());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onClickListener(View view, int position) {
        OportunidadeEmpregoAdapter adapter = (OportunidadeEmpregoAdapter) mRecyclerView.getAdapter();
        OportunidadeEmpregoDTO item = adapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), AvaliacaoCurriculoActivity.class);
        intent.putExtra("oportunidadeEmprego", item);
        startActivity(intent);

    }

    private void getViewById() {
        toolbar = (Toolbar) findViewById(R.id.activity_empresas_favoritas_toolbar);
        mSpinnerFiltroAreaProfissional = (Spinner) findViewById(R.id.activity_empresas_favoritas_spinner_filtro);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_empresas_favoritas_lista);
        activity_empresas_favoritas_toolbar_spinner = (Toolbar) findViewById(R.id.activity_empresas_favoritas_toolbar_spinner);
    }

    private void constructorToolbar() {
        toolbar.setTitle("Oportunidades de Emprego");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void constructorRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
    }

    private void enqueueOportunidadeEmpregoService(OportunidadeEmpregoService oportunidadeEmpregoService) {

        Call<List<OportunidadeEmpregoDTO>> callOportunidadeEmprego = oportunidadeEmpregoService.listar();

        callOportunidadeEmprego.enqueue(new Callback<List<OportunidadeEmpregoDTO>>() {
            @Override
            public void onResponse(Call<List<OportunidadeEmpregoDTO>> call, Response<List<OportunidadeEmpregoDTO>> response) {
                List<OportunidadeEmpregoDTO> body = response.body();

                if (!body.isEmpty()) {
                    OportunidadeEmpregoAdapter oportunidadeEmpregoAdapter = new OportunidadeEmpregoAdapter(getApplicationContext(), body);
                    oportunidadeEmpregoAdapter.setRecyclerViewOnClickListenerHack(OportunidadesEmpregoActivity.this);
                    mRecyclerView.setAdapter(oportunidadeEmpregoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<OportunidadeEmpregoDTO>> call, Throwable t) {
                OportunidadeEmpregoAdapter oportunidadeEmpregoAdapter = new OportunidadeEmpregoAdapter(getApplicationContext(), new ArrayList<OportunidadeEmpregoDTO>());
                oportunidadeEmpregoAdapter.setRecyclerViewOnClickListenerHack(OportunidadesEmpregoActivity.this);
                mRecyclerView.setAdapter(oportunidadeEmpregoAdapter);
            }
        });
    }

    private OportunidadeEmpregoService getOportunidadeEmpregoService() {
        RetrofitInicializador retrofitInicializador = new RetrofitInicializador(false);
        return retrofitInicializador.getOportunidadeEmpregoService();
    }
}
