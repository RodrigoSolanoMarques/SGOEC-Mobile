package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.fragment.CadastrarCursoComplementarFragment;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

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
                .inflate(R.layout.view_holder_lista_simple_double, parent, false);

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

            curso = (TextView) view.findViewById(R.id.view_holder_text1);
            instituicao = (TextView) view.findViewById(R.id.view_holder_text2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Snackbar sb = Snackbar.make(v, "EDITAR", Snackbar.LENGTH_LONG);
                    sb.setActionTextColor(context.getResources().getColor(R.color.colorAccent));

                    sb.setAction("Excluir", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                Jpdroid dataBase = Jpdroid.getInstance();

                                CursoComplementar cursoComplementar = cursoComplementares.get(getAdapterPosition());
                                dataBase.delete("cursocomplementar", "_id = ? ", new String[]{cursoComplementar.get_id().toString()});
                                cursoComplementares.remove(getAdapterPosition());
                                notifyDataSetChanged();

                            } catch (Exception e) {
                                ToastUtils.setMsgLong(context, "Erro");
                            }

                        }
                    }).setActionTextColor(context.getResources().getColor(android.R.color.holo_red_dark));


                    View sbView = sb.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            FragmentActivity applicationContext = (FragmentActivity) context;

                            CursoComplementar cursoComplementar = cursoComplementares.get(getAdapterPosition());
                            Bundle bundle = new Bundle();

                            bundle.putSerializable("cursoComplementar", cursoComplementar);

                            CadastrarCursoComplementarFragment cadastrarCursoComplementarFragment = new CadastrarCursoComplementarFragment();
                            cadastrarCursoComplementarFragment.setArguments(bundle);

                            applicationContext.getSupportFragmentManager().beginTransaction().addToBackStack("cursoComplementar").replace(R.id.activity_curso_complementar_framelayout, cadastrarCursoComplementarFragment).commit();
                            sb.dismiss();
                        }
                    });

                    sb.show();
                }
            });

        }
    }

}

