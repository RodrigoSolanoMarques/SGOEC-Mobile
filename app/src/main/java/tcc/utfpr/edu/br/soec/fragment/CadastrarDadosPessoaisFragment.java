package tcc.utfpr.edu.br.soec.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.CidadeArrayAdapter;
import tcc.utfpr.edu.br.soec.adapter.EstadoArrayAdapter;
import tcc.utfpr.edu.br.soec.model.Cidade;
import tcc.utfpr.edu.br.soec.model.Estado;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.utils.DatePickerFragment;
import tcc.utfpr.edu.br.soec.utils.Prefs;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;


public class CadastrarDadosPessoaisFragment extends Fragment {

    private Toolbar edFragmentCadastrarDadosPessoaisToolbar;
    private EditText edFragmentCadastrarDadosPessoaisNome;
    private EditText edFragmentCadastrarDadosPessoaisSobrenome;
    private EditText edFragmentCadastrarDadosPessoaisDataNascimento;
    private EditText edFragmentCadastrarDadosPessoaisCpf;
    private Spinner spFragmentCadastrarDadosPessoaisCidade; // Cidades do banco de dados
    private Spinner spFragmentCadastrarDadosPessoaisEstado; // Cidades do banco de dados
    private EditText edFragmentCadastrarDadosPessoaisRua;
    private EditText edFragmentCadastrarDadosPessoaisNumero;
    private EditText edFragmentCadastrarDadosPessoaisBairro;
    private EditText edFragmentCadastrarDadosPessoaisComplemento;
    private EditText edFragmentCadastrarDadosPessoaisTelefone1;
    private EditText edFragmentCadastrarDadosPessoaisTelefone2;
    private EditText edFragmentCadastrarDadosPessoaisCelular1;
    private EditText edFragmentCadastrarDadosPessoaisCelular2;

    /* Interface */
    private FragmentListener fragmentListener;

    private Pessoa pessoa;
    Jpdroid dataBase;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            fragmentListener = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar a interface FragmentListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        /* Bundle - Recupera os argumentos passados por parametros */
        recuperarBundle();

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = recuperarViews(inflater, container);

        edFragmentCadastrarDadosPessoaisToolbar.setTitle("Cadastro Básico");
        edFragmentCadastrarDadosPessoaisToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(edFragmentCadastrarDadosPessoaisToolbar);

        setHasOptionsMenu(true);

        dataBase = Jpdroid.getInstance();
        dataBase.setContext(getActivity());
        dataBase.open();

        List<Estado> estados = dataBase.retrieve(Estado.class);
        EstadoArrayAdapter estadoArrayAdapter = new EstadoArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, estados);
        spFragmentCadastrarDadosPessoaisEstado.setAdapter(estadoArrayAdapter);
        spFragmentCadastrarDadosPessoaisEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int pos, long id) {
                Estado estado = (Estado) parentView.getItemAtPosition(pos);

                List<Cidade> cidades = dataBase.retrieve(Cidade.class, "idEstado = " + estado.get_id());

                CidadeArrayAdapter cidadeArrayAdapter = new CidadeArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, cidades);
                spFragmentCadastrarDadosPessoaisCidade.setAdapter(cidadeArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edFragmentCadastrarDadosPessoaisDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment(edFragmentCadastrarDadosPessoaisDataNascimento);
                dialogFragment.show(getFragmentManager(), "DatePicker Final");
            }
        });

        pessoa = new Pessoa();


        return layout;
    }

    @NonNull
    private View recuperarViews(LayoutInflater inflater, ViewGroup container) {
        View layout = inflater.inflate(R.layout.fragment_cadastrar_dados_pessoais, container, false);

        edFragmentCadastrarDadosPessoaisToolbar = (Toolbar) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_toolbar);
        edFragmentCadastrarDadosPessoaisNome = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_nome);
        edFragmentCadastrarDadosPessoaisSobrenome = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_sobrenome);
        edFragmentCadastrarDadosPessoaisDataNascimento = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_data_nascimento);
        edFragmentCadastrarDadosPessoaisCpf = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_cpf);
        spFragmentCadastrarDadosPessoaisCidade = (Spinner) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_cidade);
        spFragmentCadastrarDadosPessoaisEstado = (Spinner) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_estado);
        edFragmentCadastrarDadosPessoaisRua = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_rua);
        edFragmentCadastrarDadosPessoaisNumero = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_numero);
        edFragmentCadastrarDadosPessoaisBairro = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_bairro);
        edFragmentCadastrarDadosPessoaisComplemento = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_complemento);
        edFragmentCadastrarDadosPessoaisTelefone1 = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_telefone1);
        edFragmentCadastrarDadosPessoaisTelefone2 = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_telefone2);
        edFragmentCadastrarDadosPessoaisCelular1 = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_celular1);
        edFragmentCadastrarDadosPessoaisCelular2 = (EditText) layout.findViewById(R.id.fragment_cadastrar_dados_pessoais_celular2);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cadastrar_dados_pessoais, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (R.id.action_salvar == itemId) {

            salvar();
            return true;
        }

        return false;
    }

    private void recuperarBundle() {

        Bundle bundle = getArguments();

        if (bundle == null) {
            limpar();
            return;
        }

        if (!bundle.containsKey("pessoa")) {
            return;
        }

        pessoa = (Pessoa) bundle.getSerializable("pessoa");

        edFragmentCadastrarDadosPessoaisNome.setText(pessoa.getNome());
        edFragmentCadastrarDadosPessoaisSobrenome.setText(pessoa.getSobrenome());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edFragmentCadastrarDadosPessoaisDataNascimento.setText(sdf.format(pessoa.getDataNascimento()));

        edFragmentCadastrarDadosPessoaisCpf.setText(pessoa.getCpf());

        EstadoArrayAdapter adapterEstado = (EstadoArrayAdapter)spFragmentCadastrarDadosPessoaisEstado.getAdapter();
        int positionEstado = adapterEstado.getPosition(pessoa.getCidade().getEstado());
        spFragmentCadastrarDadosPessoaisEstado.setSelection(positionEstado);

        CidadeArrayAdapter adapterCidade = (CidadeArrayAdapter)spFragmentCadastrarDadosPessoaisCidade.getAdapter();
        int positionCidade = adapterCidade.getPosition(pessoa.getCidade());
        spFragmentCadastrarDadosPessoaisCidade.setSelection(positionCidade);

        edFragmentCadastrarDadosPessoaisRua.setText(pessoa.getRua());
        edFragmentCadastrarDadosPessoaisNumero.setText(pessoa.getNumero());
        edFragmentCadastrarDadosPessoaisBairro.setText(pessoa.getBairro());
        edFragmentCadastrarDadosPessoaisComplemento.setText(pessoa.getComplemento());
        edFragmentCadastrarDadosPessoaisTelefone1.setText(pessoa.getTelefone1());
        edFragmentCadastrarDadosPessoaisTelefone2.setText(pessoa.getTelefone2());
        edFragmentCadastrarDadosPessoaisCelular1.setText(pessoa.getCelular1());
        edFragmentCadastrarDadosPessoaisCelular2.setText(pessoa.getCelular2());
    }

    public boolean validarCampos() {

        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisNome.getText().toString())) {
            edFragmentCadastrarDadosPessoaisNome.requestFocus();
            edFragmentCadastrarDadosPessoaisNome.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisSobrenome.getText().toString())) {
            edFragmentCadastrarDadosPessoaisSobrenome.requestFocus();
            edFragmentCadastrarDadosPessoaisSobrenome.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisDataNascimento.getText().toString())) {
            edFragmentCadastrarDadosPessoaisDataNascimento.requestFocus();
            edFragmentCadastrarDadosPessoaisDataNascimento.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisCpf.getText().toString())) {
            edFragmentCadastrarDadosPessoaisCpf.requestFocus();
            edFragmentCadastrarDadosPessoaisCpf.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisRua.getText().toString())) {
            edFragmentCadastrarDadosPessoaisRua.requestFocus();
            edFragmentCadastrarDadosPessoaisRua.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisNumero.getText().toString())) {
            edFragmentCadastrarDadosPessoaisNumero.requestFocus();
            edFragmentCadastrarDadosPessoaisNumero.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        if (TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisBairro.getText().toString())) {
            edFragmentCadastrarDadosPessoaisBairro.requestFocus();
            edFragmentCadastrarDadosPessoaisBairro.setError(getString(R.string.campo_obrigatorio));
            return false;
        }


        return true;
    }

    public void limpar() {
        edFragmentCadastrarDadosPessoaisNome.setText("");
        edFragmentCadastrarDadosPessoaisSobrenome.setText("");
        edFragmentCadastrarDadosPessoaisCpf.setText("");
        spFragmentCadastrarDadosPessoaisCidade.setSelection(0);
        edFragmentCadastrarDadosPessoaisRua.setText("");
        edFragmentCadastrarDadosPessoaisNumero.setText("");
        edFragmentCadastrarDadosPessoaisBairro.setText("");
        edFragmentCadastrarDadosPessoaisComplemento.setText("");
        edFragmentCadastrarDadosPessoaisTelefone1.setText("");
        edFragmentCadastrarDadosPessoaisTelefone2.setText("");
        edFragmentCadastrarDadosPessoaisCelular1.setText("");
        edFragmentCadastrarDadosPessoaisCelular2.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edFragmentCadastrarDadosPessoaisDataNascimento.setText(sdf.format(new Date()));

        pessoa = new Pessoa();
    }

    public void salvar() {
        try {

            if (validarCampos()) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                pessoa.setNome(edFragmentCadastrarDadosPessoaisNome.getText().toString());
                pessoa.setSobrenome(edFragmentCadastrarDadosPessoaisSobrenome.getText().toString());
                Date DataNascimento = sdf.parse(edFragmentCadastrarDadosPessoaisDataNascimento.getText().toString());
                pessoa.setDataNascimento(DataNascimento);
                pessoa.setCpf(edFragmentCadastrarDadosPessoaisCpf.getText().toString());
                pessoa.setCidade((Cidade) spFragmentCadastrarDadosPessoaisCidade.getSelectedItem());
                pessoa.setRua(edFragmentCadastrarDadosPessoaisRua.getText().toString());
                pessoa.setNumero(Integer.parseInt(edFragmentCadastrarDadosPessoaisNumero.getText().toString()));
                pessoa.setBairro(edFragmentCadastrarDadosPessoaisBairro.getText().toString());

                /* OPCIONAIS */

                if (!(TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisComplemento.getText().toString()))) {
                    pessoa.setComplemento(edFragmentCadastrarDadosPessoaisComplemento.getText().toString());
                }

                if (!(TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisTelefone1.getText().toString()))) {
                    pessoa.setTelefone1(edFragmentCadastrarDadosPessoaisTelefone1.getText().toString());
                }

                if (!(TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisTelefone2.getText().toString()))) {
                    pessoa.setTelefone2(edFragmentCadastrarDadosPessoaisTelefone2.getText().toString());
                }

                if (!(TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisCelular1.getText().toString()))) {
                    pessoa.setCelular1(edFragmentCadastrarDadosPessoaisCelular1.getText().toString());
                }

                if (!(TextUtils.isEmpty(edFragmentCadastrarDadosPessoaisCelular2.getText().toString()))) {
                    pessoa.setCelular2(edFragmentCadastrarDadosPessoaisCelular2.getText().toString());
                }

                // Verifica para sempre ter penas um cadastro de pessoa
                List<Pessoa> pessoas = dataBase.retrieve(Pessoa.class, "_id = 1", true);
                if (pessoas.size() > 0) {
                    pessoa.set_id(1L);
                }

                dataBase.persist(pessoa);

                Cursor cursor = dataBase.createQuery(Pessoa.class);
                if (cursor.moveToFirst()) {
                    Prefs.setBoolean(getActivity(), Prefs.CADASTRO_PESSOA, true);
                    ToastUtils.setMsgShort(getActivity(), getResources().getString(R.string.cadastro_salvo_com_sucesso) /*cursor.getString(cursor.getColumnIndex("nome"))*/);
                }

                fechar();
            }

        } catch (JpdroidException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            Prefs.setBoolean(getActivity(), Prefs.CADASTRO_PESSOA, false);
            ToastUtils.setMsgShort(getActivity(), getResources().getString(R.string.error_salvar));
        }

    }

    private void fechar() {
        fragmentListener.callbackCadastrarDadosPessoaisFragment();
    }


    public interface FragmentListener {
        void callbackCadastrarDadosPessoaisFragment();
    }
}
