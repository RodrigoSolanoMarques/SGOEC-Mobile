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
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;
import tcc.utfpr.edu.br.soec.utils.DatePickerFragment;

/**
 * Created by rodri on 15/03/2017.
 */

public class CadastrarExperienciaProfissionalFragment extends Fragment {

    private EditText edNomeEmpresa;
    private EditText edCargo;
    private EditText edDataInicial;
    private EditText edDataFinal;
    private EditText edAtividades;
    private CheckBox chbIsAtual;
    private TextInputLayout tilDataFinal;
    private Button btnSalvar;
    private Button btnCancelar;

    private View layout;
    private ExperienciaProfissional experienciaProfissional;

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

        layout = inflater.inflate(R.layout.fragment_cadastrar_experiencia_profissional, container, false);

        /* Recupera Objetos */
        recuperarView(layout);

         /* Eventos Listener */
        chbIsAtual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tilDataFinal.setVisibility(View.GONE);
                    edDataFinal.setText("");

                } else {
                    tilDataFinal.setVisibility(View.VISIBLE);
                    edDataFinal.setText("");
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
                salvarExperienciaProfissional();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharExperienciaProfissional();
            }
        });

        return layout;
    }


    private void recuperarView(View view) {
        edNomeEmpresa = (EditText) view.findViewById(R.id.fragment_experiencia_profissional_nome_empresa);
        edCargo = (EditText) view.findViewById(R.id.fragment_experiencia_profissional_cargo);
        edAtividades = (EditText) view.findViewById(R.id.fragment_experiencia_profissional_atividades);
        chbIsAtual = (CheckBox) view.findViewById(R.id.fragment_experiencia_profissional_isAtual);
        edDataInicial = (EditText) view.findViewById(R.id.fragment_experiencia_profissional_data_inicial);
        edDataFinal = (EditText) view.findViewById(R.id.fragment_experiencia_profissional_data_final);
        tilDataFinal = (TextInputLayout) view.findViewById(R.id.fragment_experiencia_profissional_text_input_layout_data_final);

        btnSalvar = (Button) view.findViewById(R.id.fragment_experiencia_profissional_btnSalvar);
        btnCancelar = (Button) view.findViewById(R.id.fragment_experiencia_profissional_btnCancelar);
    }

    private void limparExperienciaProfissional() {
        edNomeEmpresa.setText("");
        edCargo.setText("");
        chbIsAtual.setChecked(false);
        edAtividades.setText("");
        edDataFinal.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edDataInicial.setText(sdf.format(new Date()));
    }

    private void recuperarBundle() {

        Bundle bundle = getArguments();

        if (bundle != null) {

            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (experienciaProfissional == null) {
                experienciaProfissional = (ExperienciaProfissional) bundle.getSerializable("experienciaProfissional");
            }

            String nomeEmpresa = experienciaProfissional.getNomeEmpresa();
            String cargo = experienciaProfissional.getCargo();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String dataInical = "";
            String dataFinal = "";
            String atividades = "";

            if (experienciaProfissional.getDataInicial() != null) {
                dataInical = sdf.format(experienciaProfissional.getDataInicial());
            }

            if (experienciaProfissional.getDataFinal() != null) {
                dataFinal = sdf.format(experienciaProfissional.getDataFinal());
            }

            if (experienciaProfissional.getAtividades() != null) {
                atividades = experienciaProfissional.getAtividades().toString();
            }

            if (!TextUtils.isEmpty(nomeEmpresa)) {
                edNomeEmpresa.setText(nomeEmpresa);
            }

            if (!TextUtils.isEmpty(cargo)) {
                edCargo.setText(cargo);
            }

            edDataInicial.setText(dataInical);
            chbIsAtual.setChecked(experienciaProfissional.getAtual());

            if(experienciaProfissional.getAtual()){
                edDataFinal.setVisibility(View.VISIBLE);
                edDataFinal.setText(dataFinal);
            }else{
                edDataFinal.setVisibility(View.GONE);
            }
            edAtividades.setText(atividades);

        } else {
            // Ver se ao destruir o fragment fica null o cursoComplentar
            if (experienciaProfissional == null) {
                experienciaProfissional = new ExperienciaProfissional();
            }

            limparExperienciaProfissional();
        }
    }

    private void fecharExperienciaProfissional() {
        getFragmentManager().popBackStack();
    }

    private void salvarExperienciaProfissional() {
        try {

            if (TextUtils.isEmpty(edNomeEmpresa.getText().toString())) {
                edNomeEmpresa.requestFocus();
                edNomeEmpresa.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (TextUtils.isEmpty(edCargo.getText().toString())) {
                edCargo.requestFocus();
                edCargo.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (TextUtils.isEmpty(edDataInicial.getText().toString())) {
                edDataInicial.requestFocus();
                edDataInicial.setError(getString(R.string.campo_obrigatorio));
                return;
            }

            if (chbIsAtual.isChecked()) {
                if (TextUtils.isEmpty(edDataFinal.getText().toString())) {
                    edDataFinal.requestFocus();
                    edDataFinal.setError(getString(R.string.campo_obrigatorio));
                    return;
                }
            }

            Jpdroid dataBase = Jpdroid.getInstance();

            experienciaProfissional.setNomeEmpresa(edNomeEmpresa.getText().toString());
            experienciaProfissional.setCargo(edCargo.getText().toString());
            experienciaProfissional.setAtual(chbIsAtual.isChecked());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicial = sdf.parse(edDataInicial.getText().toString());
            experienciaProfissional.setDataInicial(dataInicial);

            if (chbIsAtual.isChecked()) {
                Date dataFinal = sdf.parse(edDataFinal.getText().toString());
                experienciaProfissional.setDataFinal(dataFinal);
            }

            if(!TextUtils.isEmpty(edAtividades.getText().toString())){
                experienciaProfissional.setAtividades(edAtividades.getText().toString());
            }

            dataBase.persist(experienciaProfissional);

            Snackbar.make(layout, getString(R.string.registro_salvado_com_sucesso), Snackbar.LENGTH_LONG).show();

            fecharExperienciaProfissional();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JpdroidException e) {
            e.printStackTrace();
        }
    }
}
