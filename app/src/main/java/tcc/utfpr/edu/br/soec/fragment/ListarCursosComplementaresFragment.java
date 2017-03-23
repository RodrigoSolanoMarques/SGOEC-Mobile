package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.CursoComplementarAdapter;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.model.Formacao;

/**
 * Created by rodri on 22/03/2017.
 */

public class ListarCursosComplementaresFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_listar_cursos_complementares, container, false);
        RecyclerView lista = (RecyclerView) layout.findViewById(R.id.fragment_listar_cursos_complementares_lista);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        lista.setLayoutManager(layoutManager);

        Jpdroid dataBase = Jpdroid.getInstance();
        List<CursoComplementar> listaCursoComplementar = dataBase.retrieve(CursoComplementar.class);

        CursoComplementarAdapter adapter = new CursoComplementarAdapter(listaCursoComplementar, getContext());
        lista.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fragment_listar_cursos_complementares_novo_curso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_main_framelayout, new CadastrarCursoComplementarFragment()).commit();
            }
        });

        return layout;
    }
}
