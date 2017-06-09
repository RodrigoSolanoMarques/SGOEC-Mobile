package tcc.utfpr.edu.br.soec.fragment;


import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
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
import tcc.utfpr.edu.br.soec.adapter.CurriculoAdapter;
import tcc.utfpr.edu.br.soec.adapter.FormacaoAdapter;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.model.Pessoa;


public class ListarCurriculosFragment extends Fragment {

    private FragmentListener mFragmentListener;
    private Jpdroid dataBase;

    public interface FragmentListener {
        void cadastrarCurriculo(Curriculo curriculo);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Verefica se a activity/context que chamou o fragment implementa a interface
        try {
            mFragmentListener = (FragmentListener) context;

            dataBase = Jpdroid.getInstance();
            dataBase.setContext(context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener da classe ListarCurriculosFragment.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_listar_curriculos, container, false);
        RecyclerView lista = (RecyclerView) layout.findViewById(R.id.fragment_listar_curriculo_lista);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        lista.setLayoutManager(layoutManager);

        List<Curriculo> listaCurriculo = dataBase.retrieve(Curriculo.class, true);

        CurriculoAdapter adapter = new CurriculoAdapter(listaCurriculo, getActivity());
        lista.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fragment_listar_curriculo_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Curriculo curriculo = new Curriculo();
                List<Pessoa> pessoas = dataBase.retrieve(Pessoa.class);

                Pessoa pessoa = pessoas.get(0);

                Candidato candidato = new Candidato();
                candidato.setPessoa(pessoa);

                curriculo.setCandidato(candidato);

                mFragmentListener.cadastrarCurriculo(curriculo);
            }
        });

        return layout;
    }

}