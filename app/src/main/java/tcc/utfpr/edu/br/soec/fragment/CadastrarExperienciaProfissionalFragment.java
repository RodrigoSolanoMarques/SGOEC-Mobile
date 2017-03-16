package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.utfpr.edu.br.soec.R;

/**
 * Created by rodri on 15/03/2017.
 */

public class CadastrarExperienciaProfissionalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_experiencia_profissional, container, false);

        return view;
    }
}
