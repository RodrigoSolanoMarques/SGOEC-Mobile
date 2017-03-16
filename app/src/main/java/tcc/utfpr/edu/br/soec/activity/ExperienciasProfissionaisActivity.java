package tcc.utfpr.edu.br.soec.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.fragment.CadastrarExperienciaProfissionalFragment;

public class ExperienciasProfissionaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencias_profissionais);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.add(R.id.activity_experiencias_profissionais_framelayout,
                new CadastrarExperienciaProfissionalFragment(),
                "fragmentExperienciaProfissional");
        tx.commit();
    }
}
