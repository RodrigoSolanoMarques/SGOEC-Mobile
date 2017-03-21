package tcc.utfpr.edu.br.soec.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.utils.DatePickerFragment;

/**
 * Created by rodri on 15/03/2017.
 */

public class CadastrarCursoComplementarFragment extends Fragment {

    private EditText nomeCurso;
    private EditText instituicao;
    private EditText dataInicial;
    private EditText dataFinal;
    private CheckBox isConcluido;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastrar_curso_complementar, container, false);

        nomeCurso = (EditText) view.findViewById(R.id.fragment_curso_complementar_nome_curso);
        instituicao = (EditText) view.findViewById(R.id.fragment_curso_complementar_instituicao);
        dataInicial = (EditText) view.findViewById(R.id.fragment_curso_complementar_data_inicial);
        dataFinal = (EditText) view.findViewById(R.id.fragment_curso_complementar_data_final);
        isConcluido = (CheckBox) view.findViewById(R.id.fragment_curso_complementar_isConcluido);


        isConcluido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dataFinal.setVisibility(View.VISIBLE);
                    return;
                }
                dataFinal.setVisibility(View.GONE);
                dataFinal.setText("");
            }
        });

        dataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment(dataInicial);
                dialogFragment.show(getFragmentManager(), "DatePicker Final");
            }
        });

        dataFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment(dataFinal);
                dialogFragment.show(getFragmentManager(), "DatePicker Final");
            }
        });

        return view;
    }
}
