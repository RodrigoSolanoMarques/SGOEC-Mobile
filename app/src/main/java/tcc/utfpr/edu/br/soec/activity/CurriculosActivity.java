package tcc.utfpr.edu.br.soec.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.CurriculoAdapter;
import tcc.utfpr.edu.br.soec.fragment.ListarCurriculosFragment;
import tcc.utfpr.edu.br.soec.fragment.ListarFormacoesFragment;
import tcc.utfpr.edu.br.soec.model.Curriculo;

public class CurriculosActivity extends AppCompatActivity implements ListarCurriculosFragment.FragmentListener, CurriculoAdapter.OnAdapterOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculos);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.activity_curriculo_frameLayout, new ListarCurriculosFragment());
        tx.commit();
    }

    @Override
    // CurriculoAdapter
    public void editarCurriculo(Curriculo curriculo) {
        cadastrarCurriculo(curriculo);
    }

    @Override
    // CadastrarCurriculoActivity
    public void cadastrarCurriculo(Curriculo curriculo) {
        Intent intent = new Intent(this, CadastrarCurriculoActivity.class);
        intent.putExtra("curriculo", curriculo);

        startActivity(intent);
    }
}
