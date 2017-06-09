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
import tcc.utfpr.edu.br.soec.fragment.CadastrarFormacaoFragment;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 22/03/2017.
 */

public class FormacaoAdapter extends RecyclerView.Adapter {

    private List<Formacao> formacoes;
    private Context context;

    public FormacaoAdapter(List<Formacao> formacoes, Context context) {
        this.formacoes = formacoes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_lista_simple_double, parent, false);

        FormacaoViewHolder holder = new FormacaoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        FormacaoViewHolder holder = (FormacaoViewHolder) viewHolder;

        Formacao formacao = formacoes.get(position);

        holder.curso.setText(formacao.getNomeCurso());
        holder.instituicao.setText(formacao.getInstituicao());

    }

    @Override
    public int getItemCount() {
        return formacoes.size();
    }

    public class FormacaoViewHolder extends RecyclerView.ViewHolder {

        private TextView curso;
        private TextView instituicao;

        public FormacaoViewHolder(View view) {
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

                                Formacao formacao = formacoes.get(getAdapterPosition());
                                dataBase.delete("formacao", "_id = ? ", new String[]{String.valueOf(formacao.get_id())});
                                formacoes.remove(getAdapterPosition());
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

                            Formacao formacao = formacoes.get(getAdapterPosition());
                            Bundle bundle = new Bundle();

                            bundle.putSerializable("formacao", formacao);


                            CadastrarFormacaoFragment cadastrarFormacaoFragment = new CadastrarFormacaoFragment();
                            cadastrarFormacaoFragment.setArguments(bundle);

                            applicationContext.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_formacoes_framelayout, cadastrarFormacaoFragment).commit();
                            sb.dismiss();
                        }
                    });

                    sb.show();
                }
            });

        }
    }

}

