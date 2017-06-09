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
import tcc.utfpr.edu.br.soec.model.CursoComplementar;

/**
 * Created by rodri on 25/05/2017.
 */

public class CursoComplementarCheckBoxAdapter extends RecyclerView.Adapter<CursoComplementarCheckBoxAdapter.ViewHolder> {


    private List<CursoComplementar> mListaCursoComplementar;
    private List<CursoComplementar> mListaCursoComplementaresSelecionadas;
    private Context mContext;

    public CursoComplementarCheckBoxAdapter(List<CursoComplementar> mListaCursoComplementar, List<CursoComplementar> mListaCursoComplementaresSelecionadas, Context context) {
        this.mListaCursoComplementar = mListaCursoComplementar;
        this.mListaCursoComplementaresSelecionadas = mListaCursoComplementaresSelecionadas != null ? mListaCursoComplementaresSelecionadas : new ArrayList<CursoComplementar>();
        this.mContext = context;
    }

    @Override
    public CursoComplementarCheckBoxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(mContext).inflate(R.layout.view_holder_lista_with_checkbox, parent, false);

        ViewHolder viewHolder = new ViewHolder(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CursoComplementarCheckBoxAdapter.ViewHolder holder, int position) {

        final CursoComplementar cursoComplementar = mListaCursoComplementar.get(position);

        holder.mInstituicao.setText(cursoComplementar.getInstituicao());
        holder.mCurso.setText(cursoComplementar.getNomeCurso());

        if (mListaCursoComplementaresSelecionadas.contains(cursoComplementar)) {
            holder.mIsSelect.setChecked(true);
        }

        holder.mIsSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mListaCursoComplementaresSelecionadas.add(cursoComplementar);
                } else {
                    mListaCursoComplementaresSelecionadas.remove(cursoComplementar);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaCursoComplementar.size();
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

    public List<CursoComplementar> getCursoComplementaresSelecionadas() {
        return mListaCursoComplementaresSelecionadas;
    }

}
