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
import tcc.utfpr.edu.br.soec.fragment.CadastrarExperienciaProfissionalFragment;
import tcc.utfpr.edu.br.soec.fragment.CadastrarFormacaoFragment;
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

public class ExperienciaProfissionalAdapter extends RecyclerView.Adapter {

    private List<ExperienciaProfissional> experienciasProfissionais;
    private Context context;

    public ExperienciaProfissionalAdapter(List<ExperienciaProfissional> experienciasProfissionais, Context context) {
        this.experienciasProfissionais = experienciasProfissionais;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_lista_simple_double, parent, false);

        ExperienciaProfissionalViewHolder holder = new ExperienciaProfissionalViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ExperienciaProfissionalViewHolder holder = (ExperienciaProfissionalViewHolder) viewHolder;

        ExperienciaProfissional experienciaProfissional = experienciasProfissionais.get(position);

        holder.nomeEmpresa.setText(experienciaProfissional.getNomeEmpresa());
        holder.cargo.setText(experienciaProfissional.getCargo());

    }

    @Override
    public int getItemCount() {
        return experienciasProfissionais.size();
    }

    public class ExperienciaProfissionalViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeEmpresa;
        private TextView cargo;

        public ExperienciaProfissionalViewHolder(View view) {
            super(view);

            nomeEmpresa = (TextView) view.findViewById(R.id.view_holder_text1);
            cargo = (TextView) view.findViewById(R.id.view_holder_text2);

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

                                ExperienciaProfissional experienciaProfissional = experienciasProfissionais.get(getAdapterPosition());
                                dataBase.delete("experienciaprofissional", "_id = ? ", new String[]{experienciaProfissional.get_id().toString()});
                                experienciasProfissionais.remove(getAdapterPosition());
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

                            ExperienciaProfissional experienciaProfissional = experienciasProfissionais.get(getAdapterPosition());
                            Bundle bundle = new Bundle();

                            bundle.putSerializable("experienciaProfissional", experienciaProfissional);


                            CadastrarExperienciaProfissionalFragment cadastrarExperienciaProfissionalFragment = new CadastrarExperienciaProfissionalFragment();
                            cadastrarExperienciaProfissionalFragment.setArguments(bundle);

                            applicationContext.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_experiencias_profissionais_framelayout, cadastrarExperienciaProfissionalFragment).commit();
                            sb.dismiss();
                        }
                    });

                    sb.show();
                }
            });

        }
    }

}

