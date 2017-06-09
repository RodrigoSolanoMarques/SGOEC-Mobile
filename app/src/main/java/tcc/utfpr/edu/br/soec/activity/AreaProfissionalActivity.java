package tcc.utfpr.edu.br.soec.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.AreaProfissionalAdapter;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.interfaces.RecyclerViewOnClickListenerHack;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;
import tcc.utfpr.edu.br.soec.model.Empresa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.service.AreaProfissionalService;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

public class AreaProfissionalActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {
    // mudar de empresas favoritas para area profissional search
    private RecyclerView mRecyclerView;
    private Spinner mSpinnerFiltroAreaProfissional;
    private List<AreaProfissionalDTO> mList;
    private List<AreaProfissionalDTO> mlistAux;
    private List<AreaProfissional> retorno = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas_favoritas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_empresas_favoritas_toolbar);
        toolbar.setTitle("Area Profissional");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mList = (List<AreaProfissionalDTO>) savedInstanceState.getSerializable("mList");
            mlistAux = (List<AreaProfissionalDTO>) savedInstanceState.getSerializable("mListAux");
        } else {
            mList = new ArrayList<>();

            AreaProfissionalService areaProfissionalService = new RetrofitInicializador().getAreaProfissionalService();
            Call<List<AreaProfissionalDTO>> listar = areaProfissionalService.listar();

            listar.enqueue(new Callback<List<AreaProfissionalDTO>>() {
                @Override
                public void onResponse(Call<List<AreaProfissionalDTO>> call, Response<List<AreaProfissionalDTO>> response) {
                    mList.addAll(response.body());

                    AreaProfissionalAdapter areaProfissionalAdapter = new AreaProfissionalAdapter(getApplicationContext(), mList);
                    areaProfissionalAdapter.setRecyclerViewOnClickListenerHack(AreaProfissionalActivity.this);
                    mRecyclerView.setAdapter(areaProfissionalAdapter);
                }

                @Override
                public void onFailure(Call<List<AreaProfissionalDTO>> call, Throwable t) {
                    ToastUtils.setMsgLong(getApplicationContext(), "Não foi possível carregar a lista");
                }
            });
        }
        mSpinnerFiltroAreaProfissional = (Spinner) findViewById(R.id.activity_empresas_favoritas_spinner_filtro);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_empresas_favoritas_lista);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
    }

    @Override
    public void onClickListener(View view, int position) {
        ToastUtils.setMsgLong(getApplicationContext(), "Position" + position);
        AreaProfissionalAdapter areaProfissionalAdapter = (AreaProfissionalAdapter) mRecyclerView.getAdapter();
        areaProfissionalAdapter.removeListItem(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_empresas_favoritas, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Pesquisar");
        return true;
    }

//    private class ListarAreaProfissionalAsyncTask extends AsyncTask<Void, Void, List<AreaProfissional>> {
//
//        private Context context;
//        private ProgressDialog progress;
//
//        public ListarAreaProfissionalAsyncTask(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            this.progress = new ProgressDialog(context);
//            this.progress.setMessage("Carregando");
//            this.progress.show();
//        }
//
//        @Override
//        protected List<AreaProfissional> doInBackground(Void... params) {
//
//            AreaProfissionalService areaProfissionalService = new RetrofitInicializador().getAreaProfissionalService();
//
//            Call<List<AreaProfissionalDTO>> listar = areaProfissionalService.listar();
//
//            listar.enqueue(new Callback<List<AreaProfissionalDTO>>() {
//                @Override
//                public void onResponse(Call<List<AreaProfissionalDTO>> call, Response<List<AreaProfissionalDTO>> response) {
//                    //retorno.addAll(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<List<AreaProfissionalDTO>> call, Throwable t) {
//                    ToastUtils.setMsgLong(getApplicationContext(), "Não foi possível carregar a lista");
//                }
//            });
//
//            return retorno;
//        }
//
//        @Override
//        protected void onPostExecute(List<AreaProfissional> areaProfissionais) {
//
//            if (areaProfissionais != null) {
//                AreaProfissionalActivity.this.mList.addAll(areaProfissionais);
//                mRecyclerView.setAdapter(new AreaProfissionalAdapter(context, areaProfissionais));
//            }
//
//            this.progress.dismiss();
//
//        }
//    }


}
