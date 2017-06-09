package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.Formacao;

/**
 * Created by rodri on 25/05/2017.
 */

public class FormacaoCheckBoxAdapter extends RecyclerView.Adapter<FormacaoCheckBoxAdapter.ViewHolder> {


    private List<Formacao> mListaFormacoes;
    private List<Formacao> mListaFormacoesSelecionadas;
    private Context mContext;

    public FormacaoCheckBoxAdapter(List<Formacao> mListaFormacoes, List<Formacao> mListaFormacoesSelecionadas, Context context) {
        this.mListaFormacoes = mListaFormacoes;
        this.mListaFormacoesSelecionadas = mListaFormacoesSelecionadas != null ? mListaFormacoesSelecionadas : new ArrayList<Formacao>();
        this.mContext = context;
    }

    @Override
    public FormacaoCheckBoxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(mContext).inflate(R.layout.view_holder_lista_with_checkbox, parent, false);

        ViewHolder viewHolder = new ViewHolder(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FormacaoCheckBoxAdapter.ViewHolder holder, int position) {

        final Formacao formacao = mListaFormacoes.get(position);

        holder.mInstituicao.setText(formacao.getInstituicao());
        holder.mCurso.setText(formacao.getNomeCurso());

        if (mListaFormacoesSelecionadas.contains(formacao)) {
            holder.mIsSelect.setChecked(true);
        }

        holder.mIsSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mListaFormacoesSelecionadas.add(formacao);
                } else {
                    mListaFormacoesSelecionadas.remove(formacao);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaFormacoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mCurso;
        private TextView mInstituicao;
        private CheckBox mIsSelect;

        public ViewHolder(View itemView) {
            super(itemView);
            mCurso = (TextView) itemView.findViewById(R.id.view_holder_text1);
            mInstituicao = (TextView) itemView.findViewById(R.id.view_holder_text2);
            mIsSelect = (CheckBox) itemView.findViewById(R.id.view_holder_checkbox);

        }
    }

    public List<Formacao> getFormacoesSelecionadas() {
        return mListaFormacoesSelecionadas;
    }

}
