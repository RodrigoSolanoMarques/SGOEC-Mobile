package tcc.utfpr.edu.br.soec.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.utfpr.edu.br.soec.R;


public class CadastrarCurriculoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_cadastrar_curriculo, container, false);



        return layout;
    }

}
