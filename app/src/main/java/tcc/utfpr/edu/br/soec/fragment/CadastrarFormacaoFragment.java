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
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.utils.DatePickerFragment;

/**
 * Created by rodri on 14/02/2017.
 */

public class CadastrarFormacaoFragment extends Fragment {

    private EditText edFormacao;
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
    private Formacao formacao;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        /* Bundle - Recupera os argumentos passados por parametros */
        recuperarBundle();

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Formação");

        layout = inflater.inflate(R.layout.fragment_cadastrar_formacao, container, false);

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
                salvarFormacao();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharFormacao();
            }
        });

        return layout;
    }

    private void recuperarBundle() {

        Bundle bundle = getArguments();

        if (bundle != null) {

            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (formacao == null) {
                formacao = (Formacao) bundle.getSerializable("formacao");
            }

            String nomeCurso = formacao.getNomeCurso();
            String instituicao = formacao.getInstituicao();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String dataInical = "";
            String dataFinal = "";
            String periodo = "";

            if(formacao.getDataInicial() != null){
                dataInical = sdf.format(formacao.getDataInicial());
            }

            if(formacao.getDataFinal() != null){
                dataFinal = sdf.format(formacao.getDataFinal());
            }

            if(formacao.getPeriodo() != null){
                periodo = formacao.getPeriodo().toString();
            }

            if (!TextUtils.isEmpty(nomeCurso)) {
                edFormacao.setText(nomeCurso);
            }

            if (!TextUtils.isEmpty(instituicao)) {
                edInstituicao.setText(instituicao);
            }

            chbIsConcluido.setChecked(formacao.getConcluido());

            edDataInicial.setText(dataInical);
            edDataFinal.setVisibility(View.VISIBLE);
            edDataFinal.setText(dataFinal);
            edPeriodo.setText(periodo);

        } else {

            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (formacao == null) {
                formacao = new Formacao();
            }

            limparFormacao();
        }
    }

    private void recuperarView(View view) {
        edFormacao = (EditText) view.findViewById(R.id.fragment_formacao_formacao);
        edInstituicao = (EditText) view.findViewById(R.id.fragment_formacao_instituicao);
        edDataInicial = (EditText) view.findViewById(R.id.fragment_formacao_data_inicial);
        edDataFinal = (EditText) view.findViewById(R.id.fragment_formacao_data_final);
        edPeriodo = (EditText) view.findViewById(R.id.fragment_formacao_periodo);
        chbIsConcluido = (CheckBox) view.findViewById(R.id.fragment_formacao_isConcluido);
        tilPeriodo = (TextInputLayout) view.findViewById(R.id.fragment_formacao_text_input_layout_periodo);
        tilDataFinal = (TextInputLayout) view.findViewById(R.id.fragment_formacao_text_input_layout_data_final);

        btnSalvar = (Button) view.findViewById(R.id.fragment_formacao_btnSalvar);
        btnCancelar = (Button) view.findViewById(R.id.fragment_formacao_btnCancelar);
    }

    private void salvarFormacao() {
        try {

            if (TextUtils.isEmpty(edFormacao.getText().toString())) {
                edFormacao.requestFocus();
                edFormacao.setError(getString(R.string.campo_obrigatorio));
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
                    edDataInicial.setError(getString(R.string.campo_obrigatorio));
                    return;
                }
            }

            Jpdroid dataBase = Jpdroid.getInstance();

            formacao.setNomeCurso(edFormacao.getText().toString());
            formacao.setInstituicao(edInstituicao.getText().toString());
            formacao.setConcluido(chbIsConcluido.isChecked());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicial = sdf.parse(edDataInicial.getText().toString());
            formacao.setDataInicial(dataInicial);

            if (chbIsConcluido.isChecked()) {
                Date dataFinal = sdf.parse(edDataFinal.getText().toString());
                formacao.setDataFinal(dataFinal);
            } else {
                formacao.setPeriodo(Integer.parseInt(edPeriodo.getText().toString()));
            }

            dataBase.persist(formacao);

            Snackbar.make(layout, getString(R.string.registro_salvado_com_sucesso), Snackbar.LENGTH_LONG).show();

            fecharFormacao();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JpdroidException e) {
            e.printStackTrace();
        }
    }

    private void limparFormacao() {
        edFormacao.setText("");
        edInstituicao.setText("");
        chbIsConcluido.setChecked(false);
        edPeriodo.setText("");
        edDataFinal.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edDataInicial.setText(sdf.format(new Date()));
    }

    private void fecharFormacao() {
        getFragmentManager().popBackStack();
    }
}
