package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.dto.OportunidadeEmpregoDTO;
import tcc.utfpr.edu.br.soec.interfaces.RecyclerViewOnClickListenerHack;

public class OportunidadeEmpregoAdapter extends RecyclerView.Adapter<OportunidadeEmpregoAdapter.OportunidadeEmpregoViewHolder>{

    private List<OportunidadeEmpregoDTO> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public OportunidadeEmpregoAdapter(Context context, List<OportunidadeEmpregoDTO> mList) {
        this.mList = mList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OportunidadeEmpregoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_holder_lista_detalhes, parent, false);
        OportunidadeEmpregoViewHolder oportunidadeEmpregoViewHolder = new OportunidadeEmpregoViewHolder(view);

        return oportunidadeEmpregoViewHolder;
    }

    @Override
    public void onBindViewHolder(OportunidadeEmpregoViewHolder holder, int position) {
        holder.mEmpresa.setText(mList.get(position).getCargo().getEmpresa().getNomeFantasia());
        holder.mCargo.setText("Cargo: " + mList.get(position).getCargo().getNome());
        if(mList.get(position).getIsSalario() != null && mList.get(position).getIsSalario()){
            holder.mSalario.setText("Salário: R$ " + mList.get(position).getSalario());
        }else{
            holder.mSalario.setText("");
        }
        holder.mEstado.setText(mList.get(position).getCidade().getEstado().getNome());
        holder.mCidade.setText(mList.get(position).getCidade().getNome());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(OportunidadeEmpregoDTO oportunidadeEmpregoDTO, int position){
        mList.add(oportunidadeEmpregoDTO);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public OportunidadeEmpregoDTO getItem(int position){
       return mList.get(position);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack){
        mRecyclerViewOnClickListenerHack = recyclerViewOnClickListenerHack;
    }

    public class OportunidadeEmpregoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mEmpresa;
        private TextView mCargo;
        private TextView mSalario;
        private TextView mEstado;
        private TextView mCidade;

        public OportunidadeEmpregoViewHolder(View itemView) {
            super(itemView);
            mEmpresa = (TextView) itemView.findViewById(R.id.view_holder_text1);
            mCargo = (TextView) itemView.findViewById(R.id.view_holder_text2);
            mEstado = (TextView) itemView.findViewById(R.id.view_holder_text3);
            mCidade = (TextView) itemView.findViewById(R.id.view_holder_text4);
            mSalario = (TextView) itemView.findViewById(R.id.view_holder_text5);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
            }
        }
    }
}
