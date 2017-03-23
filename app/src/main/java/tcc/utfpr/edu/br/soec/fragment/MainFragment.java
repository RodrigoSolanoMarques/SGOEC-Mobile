package tcc.utfpr.edu.br.soec.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.activity.EmpresasFavoritasActivity;
import tcc.utfpr.edu.br.soec.activity.OportunidadesEmpregoActivity;

/**
 * Created by rodri on 15/03/2017.
 */

public class MainFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private AppCompatActivity activity;
    private View layout;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();

        /* Recupera a instancia da Activity de chamada */
        activity = (AppCompatActivity)getActivity();

        /* Infla o layout do fragment */
        layout = inflater.inflate(R.layout.fragment_main, container, false);

        /* Seta a toolbar como uma ActionBar para poder usar os métodos como ActionBar */
        toolbar = (Toolbar) layout.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        /* Indica que tem opções de Menu na ActionBar */
        setHasOptionsMenu(true);

        DrawerLayout drawer = (DrawerLayout) layout.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) layout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        if(savedInstanceState != null){
            String titulo = savedInstanceState.getString("titulo");
            toolbar.setTitle(titulo);
        }

        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_atualizar) {
            return true;
        } else if (id == R.id.action_sobre) {
            return true;
        } else if (id == R.id.action_sair) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_curriculo) {
            toolbar.setTitle("Cadastro de Currículo");
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main_framelayout, new CadastrarCurriculoFragment()).commit();
        }else if (id == R.id.nav_formacoes) {
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main_framelayout, new ListarFormacoesFragment()).commit();
        } else if (id == R.id.nav_cursos_complementares) {
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main_framelayout, new ListarCursosComplementaresFragment()).commit();
        } else if (id == R.id.nav_experiencias_profissionais) {
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main_framelayout, new CadastrarExperienciaProfissionalFragment()).commit();
        }
        else if (id == R.id.nav_empresas) {
            Intent intent = new Intent(context, EmpresasFavoritasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_oportunidade_emprego) {
            Intent intent = new Intent(context, OportunidadesEmpregoActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) layout.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
