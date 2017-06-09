package tcc.utfpr.edu.br.soec.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.application.SoecApplication;
import tcc.utfpr.edu.br.soec.fragment.CadastrarDadosPessoaisFragment;
import tcc.utfpr.edu.br.soec.fragment.MainFragment;
import tcc.utfpr.edu.br.soec.utils.Prefs;

public class MainActivity extends AppCompatActivity implements CadastrarDadosPessoaisFragment.FragmentListener {

    private Jpdroid dataBase;
    private Context context = this;
    private Cursor cursor;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoecApplication application = (SoecApplication) getApplication();
        dataBase = application.dataBase();
        dataBase.open();

        /*Verificar se uma pessoa o candidato já foram criados*/
        /* Se não foram mostrar uma tela de cadastro para eles */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        if (!(Prefs.getBoolean(MainActivity.this, Prefs.CADASTRO_PESSOA))) {
            // Chamar tela para cadastrar pessoa e o candidato

            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.replace(R.id.activity_main_framelayout, new CadastrarDadosPessoaisFragment());
            mFragmentTransaction.commit();
            return;
        }

        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.replace(R.id.activity_main_framelayout, new MainFragment());
        mFragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void callbackCadastrarDadosPessoaisFragment() {
        // Finaliza o fragment CadastrarDadosPessoaisFragment
        mFragmentManager.popBackStack();

        // Inicia uma transação para chamar o fragment MainFragment
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.replace(R.id.activity_main_framelayout, new MainFragment());
        mFragmentTransaction.commit();
    }
}
