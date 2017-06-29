package tcc.utfpr.edu.br.soec.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.CadastrarCurriculoTabsAdapter;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabCandidatoFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabCursoComplementarFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabExperienciaProfissionalFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabFormacaoFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabFotoFragment;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.Pessoa;

public class CadastrarCurriculoActivity extends AppCompatActivity implements CadastrarCurriculoTabFotoFragment.FragmentListener,
        CadastrarCurriculoTabFormacaoFragment.FragmentListener,
        CadastrarCurriculoTabExperienciaProfissionalFragment.FragmentListener,
        CadastrarCurriculoTabCursoComplementarFragment.FragmentListener,
        CadastrarCurriculoTabCandidatoFragment.FragmentListener {

    private Curriculo curriculo;
    Jpdroid dataBase;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo);

        dataBase = Jpdroid.getInstance();
        dataBase.setContext(this);

        getExtras(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_curriculo_toolbar);
        toolbar.setTitle("Cadastro de Curriculo");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ViewPage
        mViewPager = (ViewPager) findViewById(R.id.activity_curriculo_vp);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new CadastrarCurriculoTabsAdapter(this, getSupportFragmentManager(), curriculo));
        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

        // Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_curriculo_tb);
        // Cria as tabs com o mesmo adapter utilizado pelo mViewPager
        tabLayout.setupWithViewPager(mViewPager);
        // cor branca no texto (o fundo azul foi definido no layout)
        int cor = ContextCompat.getColor(this, R.color.white);
        tabLayout.setTabTextColors(cor, cor);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.lightPrimaryColor));
    }

    private void getExtras(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("curriculo")) {

            curriculo = (Curriculo) extras.getSerializable("curriculo");

        } else {
            curriculo = new Curriculo();
            List<Pessoa> pessoas = dataBase.retrieve(Pessoa.class);

            Pessoa pessoa = pessoas.get(0);

            Candidato candidato = new Candidato();
            candidato.setPessoa(pessoa);

            curriculo.setCandidato(candidato);
        }
    }

    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("curriculo", curriculo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickCadastrarCurriculoTabFotoFragment() {
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onClickCadastrarCurriculoTabCandidatoFragment() {
        mViewPager.setCurrentItem(2);
    }

    @Override
    public void onClickCadastrarCurriculoTabFormacaoFragment() {
        mViewPager.setCurrentItem(3);
    }

    @Override
    public void onClickCadastrarCurriculoTabExperienciaProfissionalFragment() {
        mViewPager.setCurrentItem(4);
    }

    @Override
    public void onClickCadastrarCurriculoTabCursoComplementarFragment() {
        finish();
    }


}
