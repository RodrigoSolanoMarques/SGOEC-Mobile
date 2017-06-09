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
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;

/**
 * Created by rodri on 25/05/2017.
 */

public class ExperienciaProfissionalCheckBoxAdapter extends RecyclerView.Adapter<ExperienciaProfissionalCheckBoxAdapter.ViewHolder> {


    private List<ExperienciaProfissional> mListaExperienciaProfissional;
    private List<ExperienciaProfissional> mListaExperienciaProfissionalSelecionadas;
    private Context mContext;

    public ExperienciaProfissionalCheckBoxAdapter(List<ExperienciaProfissional> mListaExperienciaProfissional, List<ExperienciaProfissional> mListaExperienciaProfissionalSelecionadas, Context context) {

        this.mListaExperienciaProfissional = mListaExperienciaProfissional;
        this.mListaExperienciaProfissionalSelecionadas = mListaExperienciaProfissionalSelecionadas != null ? mListaExperienciaProfissionalSelecionadas : new ArrayList<ExperienciaProfissional>();
        this.mContext = context;
    }

    @Override
    public ExperienciaProfissionalCheckBoxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(mContext).inflate(R.layout.view_holder_lista_with_checkbox, parent, false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ExperienciaProfissionalCheckBoxAdapter.ViewHolder holder, int position) {

        final ExperienciaProfissional experienciaProfissional = mListaExperienciaProfissional.get(position);

        holder.mNomeEmpresa.setText(experienciaProfissional.getNomeEmpresa());
        holder.mCargo.setText(experienciaProfissional.getCargo());

        if (mListaExperienciaProfissionalSelecionadas.contains(experienciaProfissional)) {
            holder.mIsSelect.setChecked(true);
        }

        holder.mIsSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mListaExperienciaProfissionalSelecionadas.add(experienciaProfissional);
                } else {
                    mListaExperienciaProfissionalSelecionadas.remove(experienciaProfissional);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaExperienciaProfissional.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mCargo;
        private TextView mNomeEmpresa;
        private CheckBox mIsSelect;

        public ViewHolder(View itemView) {
            super(itemView);
            mCargo = (TextView) itemView.findViewById(R.id.view_holder_text1);
            mNomeEmpresa = (TextView) itemView.findViewById(R.id.view_holder_text2);
            mIsSelect = (CheckBox) itemView.findViewById(R.id.view_holder_checkbox);

        }
    }

    public List<ExperienciaProfissional> getExperienciaProfissionalSelecionadas() {
        return mListaExperienciaProfissionalSelecionadas;
    }

}
