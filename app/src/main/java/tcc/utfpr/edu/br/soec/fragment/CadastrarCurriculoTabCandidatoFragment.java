package tcc.utfpr.edu.br.soec.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;


public class CadastrarCurriculoTabCandidatoFragment extends Fragment {


    private EditText edTitulacao;
    private EditText edArea_profissional;
    private Button mBtnSalvar;
    private Curriculo mCurriculo;

    private FragmentListener mFragmentListener;
    private Jpdroid mDataBase;

    public interface FragmentListener {
        void onClickCadastrarCurriculoTabCandidatoFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mFragmentListener = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener da classe CadastrarCurriculoTabCandidatoFragment.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = getView(inflater, container);

        mDataBase = Jpdroid.getInstance();

        setOnClickViews();

        recuperarArgumentos();

        return layout;
    }

    private View getView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View layout = inflater.inflate(R.layout.fragment_cadastrar_curriculo_tab_candidato, container, false);
        mBtnSalvar = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_candidato_salvar);
        edTitulacao = (EditText) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_candidato_titulacao);
        edArea_profissional = (EditText) layout.findViewById(R.id.fragment_cadastrar_curriculo_tab_candidato_area_profissional);

        return layout;
    }

    private void setOnClickViews() {
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (TextUtils.isEmpty(edTitulacao.getText().toString())) {
                        edTitulacao.requestFocus();
                        edTitulacao.setError(getString(R.string.campo_obrigatorio));
                        return;
                    }

                    if (TextUtils.isEmpty(edArea_profissional.getText().toString())) {
                        edArea_profissional.requestFocus();
                        edArea_profissional.setError(getString(R.string.campo_obrigatorio));
                        return;
                    }


                    // Salvar o candidato no Banco
                    Candidato candidato = mCurriculo.getCandidato();
                    candidato.setTitulacao(edTitulacao.getText().toString());
                    candidato.setAreaProfissional(edArea_profissional.getText().toString());

                    mDataBase.persist(mCurriculo);

                    if (mCurriculo.get_id() == null) {

                        Cursor cursorCurriculo = mDataBase.rawQuery("SELECT MAX(_ID) as ID  FROM CURRICULO", null);
                        Cursor cursorCandidato = mDataBase.rawQuery("SELECT MAX(_ID) as ID  FROM CANDIDATO", null);

                        if(cursorCurriculo.moveToFirst()){
                            long id = cursorCurriculo.getLong(cursorCurriculo.getColumnIndex("ID"));
                            mCurriculo.set_id(id);
                        }

                        if(cursorCandidato.moveToFirst()){
                            long id = cursorCandidato.getLong(cursorCandidato.getColumnIndex("ID"));
                            candidato.set_id(id);
                        }
                    }

                    // Vai para a próxima Tab
                    mFragmentListener.onClickCadastrarCurriculoTabCandidatoFragment();
                } catch (JpdroidException e) {
                    e.printStackTrace();
                    ToastUtils.setMsgLong(getContext(), getResources().getString(R.string.error_salvar));
                }
            }
        });

    }

    private void recuperarArgumentos() {
        Bundle bundle = getArguments();

        if (bundle == null) {
            return;
        }

        if (!bundle.containsKey("curriculo")) {
            return;
        }

        mCurriculo = (Curriculo) bundle.getSerializable("curriculo");

        if (!TextUtils.isEmpty(mCurriculo.getCandidato().getTitulacao())) {
            edTitulacao.setText(mCurriculo.getCandidato().getTitulacao());
        }

        if (!TextUtils.isEmpty(mCurriculo.getCandidato().getTitulacao())) {
            edArea_profissional.setText(mCurriculo.getCandidato().getAreaProfissional());
        }
    }
}
