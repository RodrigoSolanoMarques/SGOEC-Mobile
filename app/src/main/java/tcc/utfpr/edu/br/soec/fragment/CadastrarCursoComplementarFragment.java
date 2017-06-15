package tcc.utfpr.edu.br.soec.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
    private CursoComplementar cursoComplementar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        /* Bundle - Recupera os argumentos passados por parametros */
        recuperarBundle();

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Curso Complementar");

        layout = inflater.inflate(R.layout.fragment_cadastrar_curso_complementar, container, false);

        /* Recupera Objetos */
        recuperarView(layout);

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
                edNomeCurso.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (TextUtils.isEmpty(edInstituicao.getText().toString())) {
                edInstituicao.requestFocus();
                edInstituicao.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (TextUtils.isEmpty(edDataInicial.getText().toString())) {
                edDataInicial.requestFocus();
                edDataInicial.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (chbIsConcluido.isChecked()) {
                if (TextUtils.isEmpty(edDataFinal.getText().toString())) {
                    edDataFinal.requestFocus();
                    edDataFinal.setError(getString(R.string.campo_obrigatorio));
                    return;
                }
            } else {
                if (TextUtils.isEmpty(edPeriodo.getText().toString())) {
                    edPeriodo.requestFocus();
                    edPeriodo.setError(getString(R.string.campo_obrigatorio));
                    return;
                }
            }

            Jpdroid dataBase = Jpdroid.getInstance();

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

    private void recuperarBundle() {

        Bundle bundle = getArguments();

        if (bundle != null) {

            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (cursoComplementar == null) {
                cursoComplementar = (CursoComplementar) bundle.getSerializable("cursoComplementar");
            }

            String nomeCurso = cursoComplementar.getNomeCurso();
            String instituicao = cursoComplementar.getInstituicao();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String dataInical = "";
            String dataFinal = "";
            String periodo = "";

            if(cursoComplementar.getDataInicial() != null){
                dataInical = sdf.format(cursoComplementar.getDataInicial());
            }

            if(cursoComplementar.getDataFinal() != null){
                dataFinal = sdf.format(cursoComplementar.getDataFinal());
            }

            if(cursoComplementar.getPeriodo() != null){
                periodo = cursoComplementar.getPeriodo().toString();
            }

            if (!TextUtils.isEmpty(nomeCurso)) {
                edNomeCurso.setText(nomeCurso);
            }

            if (!TextUtils.isEmpty(instituicao)) {
                edInstituicao.setText(instituicao);
            }

            chbIsConcluido.setChecked(cursoComplementar.getConcluido());

            edDataInicial.setText(dataInical);
            edDataFinal.setVisibility(View.VISIBLE);
            edDataFinal.setText(dataFinal);
            edPeriodo.setText(periodo);

        } else {

            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (cursoComplementar == null) {
                cursoComplementar = new CursoComplementar();
            }

            limparCursoComplementar();
        }
    }

    private void salvarBundle(Bundle outState) {
        Bundle bundle = new Bundle();

        //bundle.putSerializable("cursoComplementar", cursoComplementar);

        bundle.putString("nomeCurso", edNomeCurso.getText().toString());
        bundle.putString("instituicao", edInstituicao.getText().toString());
        bundle.putString("dataInical", edDataInicial.getText().toString());
        bundle.putString("dataFinal", edDataFinal.getText().toString());
        bundle.putBoolean("isConcluido", chbIsConcluido.isChecked());
        bundle.putInt("periodo", Integer.parseInt(edNomeCurso.getText().toString()));
        //bundle.putLong("_id", );
        //bundle.putLong("id",);

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

