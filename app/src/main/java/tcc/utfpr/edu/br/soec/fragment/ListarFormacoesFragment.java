package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.Formacao;

/**
 * Created by rodri on 14/02/2017.
 */

public class ListarFormacoesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_formacoes, container, false);
        ListView lista = (ListView) view.findViewById(R.id.fragment_listar_formacoes_lista);


        // Buscar do banco depois
        List<Formacao> formacoes = new ArrayList<>();

        for (int i = 1; i<= 10; i++){
            Formacao formacao = new Formacao();
            formacao.setNomeCurso("Curso " + i);
            formacao.setInstituicao("Instituicao " + i);
            formacao.setDataInicial(new Date());
            formacao.setDataFinal(new Date());
            formacao.setId((long) i);

            formacoes.add(formacao);
        }

//        ArrayAdapter<Formacao> adapter = new ArrayAdapter<Formacao>(getContext(), android.R.layout.simple_list_item_2, formacoes);

        List<String> teste = Arrays.asList("Curso 01", "Curso 02", "Curso 03", "Curso 04", "Curso 05", "Curso 06");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, teste);

        lista.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fragment_listar_formacoes_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }
}
