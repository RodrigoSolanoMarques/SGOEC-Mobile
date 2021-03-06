package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.CursoComplementarAdapter;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;

/**
 * Created by rodri on 22/03/2017.
 */

public class ListarCursosComplementaresFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.fragment_listar_cursos_complementares, container, false);
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
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_curso_complementar_framelayout, new CadastrarCursoComplementarFragment()).commit();
            }
        });

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Cursos Complementares");
    }
}
