package tcc.utfpr.edu.br.soec.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.activity.CurriculosActivity;
import tcc.utfpr.edu.br.soec.activity.CursosComplementaresActivity;
import tcc.utfpr.edu.br.soec.activity.AreaProfissionalActivity;
import tcc.utfpr.edu.br.soec.activity.ExperienciasProfissionaisActivity;
import tcc.utfpr.edu.br.soec.activity.FormacoesActivity;
import tcc.utfpr.edu.br.soec.activity.OportunidadesEmpregoActivity;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.model.Pessoa;

/**
 * Created by rodri on 15/03/2017.
 */

public class MainFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener {

    private Jpdroid dataBase;
    private Context context;
    private AppCompatActivity activity;
    private View layout;
    private Toolbar toolbar;

    /* nav_header*/
    private TextView nav_header_main_tv_nome;
    private TextView nav_header_main_email;
    private TextView nav_header_main_data_nascimento;
    private ImageView nav_header_main_foto;
    private String caminhoFoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();
        dataBase = Jpdroid.getInstance();

        /* Recupera a instancia da Activity de chamada */
        activity = (AppCompatActivity) getActivity();

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

        List<ContaUsuario> contaUsuarios = dataBase.retrieve(ContaUsuario.class);
        List<Pessoa> pessoas = dataBase.retrieve(Pessoa.class);

        NavigationView navigationView = (NavigationView) layout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        nav_header_main_tv_nome = (TextView) header.findViewById(R.id.nav_header_main_tv_nome);
        nav_header_main_email = (TextView) header.findViewById(R.id.nav_header_main_email);
        nav_header_main_data_nascimento = (TextView) header.findViewById(R.id.nav_header_main_data_nascimento);
        nav_header_main_foto = (ImageView) header.findViewById(R.id.nav_header_main_foto);

        caminhoFoto = pessoas.get(0).getFoto();

        if(!caminhoFoto.equals("null") && caminhoFoto != null){
            carregarFoto();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        nav_header_main_email.setText(contaUsuarios.get(0).getEmail());
        nav_header_main_tv_nome.setText(pessoas.get(0).getNome());
        nav_header_main_data_nascimento.setText(sdf.format(pessoas.get(0).getDataNascimento()));

        if (savedInstanceState != null) {
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
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_curriculo) {
            Intent intent = new Intent(context, CurriculosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_formacoes) {
            Intent intent = new Intent(context, FormacoesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cursos_complementares) {
            Intent intent = new Intent(context, CursosComplementaresActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_experiencias_profissionais) {
            Intent intent = new Intent(context, ExperienciasProfissionaisActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_area_profissional) {
            // mudar de empresas favoritas para area profissional
            Intent intent = new Intent(context, AreaProfissionalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_oportunidade_emprego) {

            Intent intent = new Intent(context, OportunidadesEmpregoActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_sobre) {

        } else if (id == R.id.action_sair) {
            getActivity().finish();

        }
        DrawerLayout drawer = (DrawerLayout) layout.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carregarFoto() {

        Bitmap bitmapReduzido = criarBitmapFoto();

        nav_header_main_foto.setImageBitmap(bitmapReduzido);
        nav_header_main_foto.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    private Bitmap criarBitmapFoto() {
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
        return Bitmap.createScaledBitmap(bitmap, 400, 400, true);
    }

}
