package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.FormacaoAdapter;
import tcc.utfpr.edu.br.soec.model.Formacao;

/**
 * Created by rodri on 14/02/2017.
 */

public class ListarFormacoesFragment extends Fragment {

    private AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_listar_formacoes, container, false);
        RecyclerView lista = (RecyclerView) layout.findViewById(R.id.fragment_listar_formacao_lista);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        lista.setLayoutManager(layoutManager);

        Jpdroid dataBase = Jpdroid.getInstance();
        List<Formacao> listaFormacao = dataBase.retrieve(Formacao.class);

        FormacaoAdapter adapter = new FormacaoAdapter(listaFormacao, getContext());
        lista.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fragment_listar_formacao_novo_curso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_formacoes_framelayout, new CadastrarFormacaoFragment()).commit();
            }
        });

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Formações");
    }
}
