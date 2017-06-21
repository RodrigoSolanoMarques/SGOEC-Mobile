package tcc.utfpr.edu.br.soec.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.adapter.ExperienciaProfissionalCheckBoxAdapter;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 06/04/2017.
 */

public class CadastrarCurriculoTabExperienciaProfissionalFragment extends Fragment {

    private Button mBtnSalvar;
    private Button mBtnProximo;
    private RecyclerView mLista;
    private TextView mTvNenhumaExperienciaProfissional;
    private Curriculo mCurriculo;

    private ExperienciaProfissionalCheckBoxAdapter mAdapter;
    private List<ExperienciaProfissional> listaExperienciaProfissionalCadastrada;
    private FragmentListener mFragmentListener;
    private Jpdroid mDataBase;

    public interface FragmentListener {
        void onClickCadastrarCurriculoTabExperienciaProfissionalFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mFragmentListener = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener da classe CadastrarCurriculoTabExperienciaProfissionalFragment.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = getView(inflater, container);

        mDataBase = Jpdroid.getInstance();
        listaExperienciaProfissionalCadastrada = mDataBase.retrieve(ExperienciaProfissional.class);

        setVisibilityView(listaExperienciaProfissionalCadastrada);

        setOnClickViews();

        recuperarArgumentos();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mLista.setLayoutManager(layoutManager);

        mAdapter = new ExperienciaProfissionalCheckBoxAdapter(listaExperienciaProfissionalCadastrada, mCurriculo.getExperienciasProfissionais(), getContext());
        mLista.setAdapter(mAdapter);

        return layout;
    }

    @NonNull
    private View getView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View layout = inflater.inflate(R.layout.fragment_cadastrar_curriculo_experiencia_profissional, container, false);
        mLista = (RecyclerView) layout.findViewById(R.id.fragment_cadastrar_curriculo_experiencia_profissional_recyclerView);
        mBtnSalvar = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_experiencia_profissional_btnSalvar);
        mBtnProximo = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_experiencia_profissional_btnProximo);
        mTvNenhumaExperienciaProfissional = (TextView) layout.findViewById(R.id.fragment_cadastrar_curriculo_experiencia_profissional_tvNenhumaExperienciaProfissional);

        return layout;
    }

    private void setVisibilityView(List<ExperienciaProfissional> listaExperienciaProfissional) {
        if (listaExperienciaProfissional.size() == 0) {
            mTvNenhumaExperienciaProfissional.setVisibility(View.VISIBLE);
            mBtnProximo.setVisibility(View.VISIBLE);
            mBtnSalvar.setVisibility(View.GONE);
        } else {
            mTvNenhumaExperienciaProfissional.setVisibility(View.GONE);
            mBtnProximo.setVisibility(View.GONE);
            mBtnSalvar.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClickViews() {
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Salvar as Experiencias Profissionais selecionadas no Banco
                    List<ExperienciaProfissional> experienciaProfissionalSelecionadas = mAdapter.getExperienciaProfissionalSelecionadas();
                    mCurriculo.setExperienciasProfissionais(experienciaProfissionalSelecionadas);

                    Candidato candidato =  mCurriculo.getCandidato();
                    mDataBase.persist(mCurriculo);

                    if(mCurriculo.get_id() == null){

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
                    mFragmentListener.onClickCadastrarCurriculoTabExperienciaProfissionalFragment();

                } catch (JpdroidException e) {
                    e.printStackTrace();
                    ToastUtils.setMsgLong(getContext(), getResources().getString(R.string.error_salvar));
                }
            }
        });

        mBtnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vai para a próxima Tab
                mFragmentListener.onClickCadastrarCurriculoTabExperienciaProfissionalFragment();
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
    }

}
