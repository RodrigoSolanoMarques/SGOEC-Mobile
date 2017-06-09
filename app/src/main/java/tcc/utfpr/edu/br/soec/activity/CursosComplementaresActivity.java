package tcc.utfpr.edu.br.soec.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCursoComplementarFragment;
import tcc.utfpr.edu.br.soec.fragment.ListarCursosComplementaresFragment;
import tcc.utfpr.edu.br.soec.fragment.ListarFormacoesFragment;

public class CursosComplementaresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_complentar);

        Toolbar toolbar = (Toolbar)findViewById(R.id.activity_curso_complementar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* FragmentManager */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.activity_curso_complementar_framelayout, new ListarCursosComplementaresFragment());
        tx.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
