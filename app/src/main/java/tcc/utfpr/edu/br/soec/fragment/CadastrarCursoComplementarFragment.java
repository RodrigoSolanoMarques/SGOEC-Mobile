package tcc.utfpr.edu.br.soec.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.utils.DatePickerFragment;

public class CadastrarCursoComplementarFragment extends Fragment {

    private EditText edNomeCurso;
    private EditText edInstituicao;
    private EditText edDataInicial;
    private EditText edDataFinal;
    private EditText edPeriodo;
    private CheckBox chbIsConcluido;
    private TextInputLayout tilPeriodo;
    private TextInputLayout tilDataFinal;
    private Button btnSalvar;
    private Button btnCancelar;

    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_cadastrar_curso_complementar, container, false);

        /* Recupera Objetos */
        recuperarView(layout);

        /* Bundle - Saved Instance State */
        recuperarBundle(savedInstanceState);

        /* Eventos Listener */

        chbIsConcluido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tilDataFinal.setVisibility(View.VISIBLE);
                    edDataFinal.setText("");

                    tilPeriodo.setVisibility(View.GONE);
                    edPeriodo.setText("");

                } else {
                    tilDataFinal.setVisibility(View.GONE);
                    edDataFinal.setText("");

                    tilPeriodo.setVisibility(View.VISIBLE);
                    edPeriodo.setText("");
                }
            }
        });

        edDataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment(edDataInicial);
                dialogFragment.show(getFragmentManager(), "DatePicker Final");
            }
        });

        edDataFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment(edDataFinal);
                dialogFragment.show(getFragmentManager(), "DatePicker Final");
            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCursoComplementar();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharCursoComplementar();
            }
        });

        return layout;
    }

    private void salvarCursoComplementar() {
        try {

            if (TextUtils.isEmpty(edNomeCurso.getText().toString())) {
                edNomeCurso.requestFocus();
                edNomeCurso.setError(getString(R.string.campo_obrigatório));
                return;
            }

            if (TextUtils.isEmpty(edInstituicao.getText().toString())) {
                edInstituicao.requestFocus();
                edInstituicao.setError(getString(R.string.campo_obrigatório));
                return;
            }

            if (TextUtils.isEmpty(edDataInicial.getText().toString())) {
                edDataInicial.requestFocus();
                edDataInicial.setError(getString(R.string.campo_obrigatório));
                return;
            }

            if (chbIsConcluido.isChecked()) {
                if (TextUtils.isEmpty(edDataFinal.getText().toString())) {
                    edDataFinal.requestFocus();
                    edDataFinal.setError(getString(R.string.campo_obrigatório));
                    return;
                }
            } else {
                if (TextUtils.isEmpty(edPeriodo.getText().toString())) {
                    edPeriodo.requestFocus();
                    edDataInicial.setError(getString(R.string.campo_obrigatório));
                    return;
                }
            }

            Jpdroid dataBase = Jpdroid.getInstance();

            CursoComplementar cursoComplementar = new CursoComplementar();
            cursoComplementar.setNomeCurso(edNomeCurso.getText().toString());
            cursoComplementar.setInstituicao(edInstituicao.getText().toString());
            cursoComplementar.setConcluido(chbIsConcluido.isChecked());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicial = sdf.parse(edDataInicial.getText().toString());
            cursoComplementar.setDataInicial(dataInicial);

            if (chbIsConcluido.isChecked()) {
                Date dataFinal = sdf.parse(edDataFinal.getText().toString());
                cursoComplementar.setDataFinal(dataFinal);
            } else {
                cursoComplementar.setPeriodo(Integer.parseInt(edPeriodo.getText().toString()));
            }

            dataBase.persist(cursoComplementar);

            Snackbar.make(layout, getString(R.string.registro_salvado_com_sucesso), Snackbar.LENGTH_LONG).show();

            fecharCursoComplementar();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JpdroidException e) {
            e.printStackTrace();
        }
    }

    private void fecharCursoComplementar() {
        getFragmentManager().popBackStack();
    }

    private void recuperarBundle(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {

            Bundle bundle = savedInstanceState.getBundle(getString(R.string.bundle_cadastrar_curso_complementar_fragment));

            if (bundle != null) {
                String nomeCurso = bundle.getString("nomeCurso");
                String instituicao = bundle.getString("instituicao");
                String dataInical = bundle.getString("dataInical");
                String dataFinal = bundle.getString("dataFinal");
                Boolean isConcluido = bundle.getBoolean("isConcluido");
                Integer periodo = bundle.getInt("periodo");

                if (!TextUtils.isEmpty(nomeCurso)) {
                    edNomeCurso.setText(nomeCurso);
                }

                if (!TextUtils.isEmpty(instituicao)) {
                    edInstituicao.setText(instituicao);
                }

                if (!TextUtils.isEmpty(dataInical)) {
                    edDataInicial.setText(dataInical);
                }

                if (!TextUtils.isEmpty(dataFinal)) {
                    edDataFinal.setText(dataFinal);
                }

                if (periodo > 0) {
                    edPeriodo.setText(periodo);
                }

                chbIsConcluido.setChecked(isConcluido);
            } else {
                limparCursoComplementar();
            }

        } else {
            limparCursoComplementar();
        }
    }

    private void salvarBundle(Bundle outState) {
        Bundle bundle = new Bundle();

        bundle.putString("nomeCurso", edNomeCurso.getText().toString());
        bundle.putString("instituicao", edInstituicao.getText().toString());
        bundle.putString("dataInical", edDataInicial.getText().toString());
        bundle.putString("dataFinal", edDataFinal.getText().toString());
        bundle.putBoolean("isConcluido", chbIsConcluido.isChecked());
        bundle.putInt("periodo", Integer.parseInt(edNomeCurso.getText().toString()));

        outState.putBundle(getString(R.string.bundle_cadastrar_curso_complementar_fragment), bundle);
    }

    private void limparCursoComplementar() {
        edNomeCurso.setText("");
        edInstituicao.setText("");
        chbIsConcluido.setChecked(false);
        edPeriodo.setText("");
        edDataFinal.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edDataInicial.setText(sdf.format(new Date()));
    }

    private void recuperarView(View view) {
        edNomeCurso = (EditText) view.findViewById(R.id.fragment_curso_complementar_nome_curso);
        edInstituicao = (EditText) view.findViewById(R.id.fragment_curso_complementar_instituicao);
        edDataInicial = (EditText) view.findViewById(R.id.fragment_curso_complementar_data_inicial);
        edDataFinal = (EditText) view.findViewById(R.id.fragment_curso_complementar_data_final);
        edPeriodo = (EditText) view.findViewById(R.id.fragment_curso_complementar_periodo);
        chbIsConcluido = (CheckBox) view.findViewById(R.id.fragment_curso_complementar_isConcluido);
        tilPeriodo = (TextInputLayout) view.findViewById(R.id.fragment_curso_complementar_text_input_layout_periodo);
        tilDataFinal = (TextInputLayout) view.findViewById(R.id.fragment_curso_complementar_text_input_layout_data_final);

        btnSalvar = (Button) view.findViewById(R.id.fragment_curso_complementar_btnSalvar);
        btnCancelar = (Button) view.findViewById(R.id.fragment_curso_complementar_btnCancelar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        //salvarBundle(outState);

    }


}

