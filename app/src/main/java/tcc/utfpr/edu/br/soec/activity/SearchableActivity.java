package tcc.utfpr.edu.br.soec.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.AreaProfissionalAdapter;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.interfaces.RecyclerViewOnClickListenerHack;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 29/03/2017.
 */

public class SearchableActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<AreaProfissionalDTO> mList;
    private List<AreaProfissionalDTO> mListAux;
    private AreaProfissionalAdapter areaProfissionalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        mToolbar = (Toolbar) findViewById(R.id.activity_searchable_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null){
            mList = (List<AreaProfissionalDTO>) savedInstanceState.getSerializable("mList");
            mListAux = (List<AreaProfissionalDTO>) savedInstanceState.getSerializable("mListAux");
        }else{
            mList = new ArrayList<>();
            mListAux = new ArrayList<>();

        /* Substituir lista */
            for ( int i = 0 ; i < 20 ; i++ ){
                AreaProfissionalDTO areaProfissional = new AreaProfissionalDTO();
                areaProfissional.setNome("Nome " + i);
                areaProfissional.setDescricao("Descrição " + i);

                mList.add(areaProfissional);
            }
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_searchable_lista);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        areaProfissionalAdapter = new AreaProfissionalAdapter(this, mListAux);
        areaProfissionalAdapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(areaProfissionalAdapter);

        hendleSearch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        hendleSearch(intent);
    }

    public void hendleSearch(Intent intent){
        if (intent.ACTION_SEARCH.equalsIgnoreCase( intent.getAction())){
            String q = intent.getStringExtra(SearchManager.QUERY);
            mToolbar.setTitle(q);

            filterAreaProfissional(q);
        }
    }

    public void filterAreaProfissional(String q){
        mListAux.clear();
        for (int i = 0 ; i < mList.size();  i++ ){
            if(mList.get(i).getNome().toLowerCase().startsWith(q.toLowerCase())){
                mListAux.add(mList.get(i));
            }
        }
        for (int i = 0 ; i < mList.size();  i++ ){
            if(!mListAux.contains(mList.get(i)) && mList.get(i).getDescricao().toLowerCase().startsWith(q.toLowerCase())){
                    mListAux.add(mList.get(i));

            }
        }

        areaProfissionalAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("mList", (ArrayList<AreaProfissionalDTO>) mList);
        outState.putSerializable("mListAux", (ArrayList<AreaProfissionalDTO>) mListAux);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
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

    @Override
    public void onClickListener(View view, int position) {
        ToastUtils.setMsgLong(getApplicationContext(), "Position" + position);
        AreaProfissionalAdapter areaProfissionalAdapter = (AreaProfissionalAdapter) mRecyclerView.getAdapter();
        areaProfissionalAdapter.removeListItem(position);
    }
}

