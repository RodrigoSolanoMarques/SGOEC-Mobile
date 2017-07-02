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
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 22/03/2017.
 */

public class CurriculoAdapter extends RecyclerView.Adapter {

    private List<Curriculo> curriculos;
    private Context context;
    private OnAdapterOnClickListener onAdapterOnClickListener;


    public interface OnAdapterOnClickListener{
        void editarCurriculo(Curriculo curriculo);
    }

    public CurriculoAdapter(List<Curriculo> curriculos, Context context) {
        this.curriculos = curriculos;
        this.context = context;

        try {
            onAdapterOnClickListener = (OnAdapterOnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar a interface OnAdapterOnClickListener da classe CurriculoAdapter.");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_lista_simple_double, parent, false);

        CurriculoViewHolder holder = new CurriculoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CurriculoViewHolder holder = (CurriculoViewHolder) viewHolder;

        position++;
        holder.numero.setText(String.valueOf(position));
        holder.nome.setText("Currículo");

    }

    @Override
    public int getItemCount() {
        return curriculos.size();
    }

    public class CurriculoViewHolder extends RecyclerView.ViewHolder {

        private TextView numero;
        private TextView nome;

        public CurriculoViewHolder(View view) {
            super(view);

            numero = (TextView) view.findViewById(R.id.view_holder_text1);
            nome = (TextView) view.findViewById(R.id.view_holder_text2);

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
                                dataBase.open();

                                Curriculo curriculo = curriculos.get(getAdapterPosition());
                                dataBase.delete("curriculo", "_id = ? ", new String[]{curriculo.get_id().toString()});
                                curriculos.remove(getAdapterPosition());
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

                            Curriculo curriculo = curriculos.get(getAdapterPosition());
                            onAdapterOnClickListener.editarCurriculo(curriculo);
                            sb.dismiss();
                        }
                    });

                    sb.show();
                }
            });

        }
    }

}

