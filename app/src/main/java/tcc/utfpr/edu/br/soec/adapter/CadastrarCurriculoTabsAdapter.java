package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabCandidatoFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabCursoComplementarFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabExperienciaProfissionalFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabFormacaoFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCurriculoTabFotoFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarDadosPessoaisFragment;
import tcc.utfpr.edu.br.soec.model.Curriculo;

/**
 * Created by rodri on 06/04/2017.
 */

public class CadastrarCurriculoTabsAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private Curriculo mCurriculo;

    public CadastrarCurriculoTabsAdapter(Context context, FragmentManager fm, Curriculo curriculo) {
        super(fm);
        this.mContext = context;
        this.mCurriculo = curriculo;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.foto);
        } else if (position == 1) {
            return mContext.getString(R.string.candidato);
        } else if (position == 2) {
            return mContext.getString(R.string.formacao);
        } else if (position == 3) {
            return mContext.getString(R.string.exp_profissional);
        } else {
            return mContext.getString(R.string.curso_complementar);
        }
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        if (position == 0) {
            f = new CadastrarCurriculoTabFotoFragment();
        } else if (position == 1) {
            f = new CadastrarCurriculoTabCandidatoFragment();
        } else if (position == 2) {
            f = new CadastrarCurriculoTabFormacaoFragment();
        } else if (position == 3) {
            f = new CadastrarCurriculoTabExperienciaProfissionalFragment();
        } else if (position == 4) {
            f = new CadastrarCurriculoTabCursoComplementarFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("curriculo", mCurriculo);

        f.setArguments(bundle);

        return f;
    }

}
