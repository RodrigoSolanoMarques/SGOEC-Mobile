package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;

/**
 * Created by rodri on 22/03/2017.
 */

public class CursoComplementarAdapter extends RecyclerView.Adapter {

    private List<CursoComplementar> cursoComplementares;
    private Context context;

    public CursoComplementarAdapter(List<CursoComplementar> cursoComplementares, Context context) {
        this.cursoComplementares = cursoComplementares;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.view_holder_curso_complementar, parent, false);

        CursoComplementarViewHolder holder = new CursoComplementarViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CursoComplementarViewHolder holder = (CursoComplementarViewHolder) viewHolder;

        CursoComplementar curso = cursoComplementares.get(position);

        holder.curso.setText(curso.getNomeCurso());
        holder.instituicao.setText(curso.getInstituicao());
    }

    @Override
    public int getItemCount() {
        return cursoComplementares.size();
    }

    public class CursoComplementarViewHolder extends RecyclerView.ViewHolder {

        private TextView curso;
        private TextView instituicao;

        public CursoComplementarViewHolder(View view) {
            super(view);

            curso = (TextView) view.findViewById(R.id.view_holder_curso_complementar_curso);
            instituicao = (TextView) view.findViewById(R.id.view_holder_curso_complementar_instituicao);
        }
    }

}

