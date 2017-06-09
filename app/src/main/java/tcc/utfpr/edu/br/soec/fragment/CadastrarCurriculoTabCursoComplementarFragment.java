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
import tcc.utfpr.edu.br.soec.adapter.CursoComplementarCheckBoxAdapter;
import tcc.utfpr.edu.br.soec.adapter.FormacaoCheckBoxAdapter;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 06/04/2017.
 */

public class CadastrarCurriculoTabCursoComplementarFragment extends Fragment {

    private Button mBtnSalvar;
    private Button mBtnProximo;
    private RecyclerView mLista;
    private TextView mTvNenhumaCursoComplementar;
    private Curriculo mCurriculo;

    private CursoComplementarCheckBoxAdapter mAdapter;
    private List<CursoComplementar> listaCursoComplementarCadastrada;
    private FragmentListener mFragmentListener;
    private Jpdroid mDataBase;

    public interface FragmentListener {
        void onClickCadastrarCurriculoTabCursoComplementarFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mFragmentListener = (FragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " deve implementar a interface FragmentListener da classe CadastrarCurriculoTabCursoComplementarFragment.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = getView(inflater, container);

        mDataBase = Jpdroid.getInstance();
        listaCursoComplementarCadastrada = mDataBase.retrieve(CursoComplementar.class);

        setVisibilityView(listaCursoComplementarCadastrada);

        setOnClickViews();

        recuperarArgumentos();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mLista.setLayoutManager(layoutManager);

        mAdapter = new CursoComplementarCheckBoxAdapter(listaCursoComplementarCadastrada, mCurriculo.getCursoComplementares(), getContext());
        mLista.setAdapter(mAdapter);

        return layout;
    }

    @NonNull
    private View getView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View layout = inflater.inflate(R.layout.fragment_cadastrar_curriculo_curso_complementar, container, false);
        mLista = (RecyclerView) layout.findViewById(R.id.fragment_cadastrar_curriculo_curso_complementar_recyclerView);
        mBtnSalvar = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_curso_complementar_btnSalvar);
        mBtnProximo = (Button) layout.findViewById(R.id.fragment_cadastrar_curriculo_curso_complementar_btnProximo);
        mTvNenhumaCursoComplementar = (TextView) layout.findViewById(R.id.fragment_cadastrar_curriculo_curso_complementar_tvNenhumaCursoComplementar);

        return layout;
    }

    private void setVisibilityView(List<CursoComplementar> listaCursoComplementar) {
        if (listaCursoComplementar.size() == 0) {
            mTvNenhumaCursoComplementar.setVisibility(View.VISIBLE);
            mBtnProximo.setVisibility(View.VISIBLE);
            mBtnSalvar.setVisibility(View.GONE);
        } else {
            mTvNenhumaCursoComplementar.setVisibility(View.GONE);
            mBtnProximo.setVisibility(View.GONE);
            mBtnSalvar.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClickViews() {
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Salvar os Cursos Complementares selecionadas no Banco
                    List<CursoComplementar> cursosComplementaresSelecionadas = mAdapter.getCursoComplementaresSelecionadas();
                    mCurriculo.setCursoComplementares(cursosComplementaresSelecionadas);
                    mDataBase.persist(mCurriculo);

                    if(mCurriculo.get_id() == null){

                        Cursor cursor = mDataBase.rawQuery("SELECT MAX(_ID) as ID  FROM CURRICULO", null);
                        if(cursor.moveToFirst()){
                            long id = cursor.getLong(cursor.getColumnIndex("ID"));
                            mCurriculo.set_id(id);
                        }
                    }

                    // Vai para a próxima Tab
                    mFragmentListener.onClickCadastrarCurriculoTabCursoComplementarFragment();
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
                mFragmentListener.onClickCadastrarCurriculoTabCursoComplementarFragment();
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
