package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.interfaces.RecyclerViewOnClickListenerHack;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;
import tcc.utfpr.edu.br.soec.model.Empresa;

public class AreaProfissionalAdapter extends RecyclerView.Adapter<AreaProfissionalAdapter.AreaProfissionalViewHolder>{

    private List<AreaProfissionalDTO> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public AreaProfissionalAdapter(Context context, List<AreaProfissionalDTO> mList) {
        this.mList = mList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public AreaProfissionalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_holder_lista_simple_double, parent, false);
        AreaProfissionalViewHolder areaProfissionalViewHolder = new AreaProfissionalViewHolder(view);

        return areaProfissionalViewHolder;
    }

    @Override
    public void onBindViewHolder(AreaProfissionalViewHolder holder, int position) {
        holder.mNome.setText(mList.get(position).getNome());
        holder.mDescricao.setText(mList.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(AreaProfissionalDTO areaProfissional, int position){
        mList.add(areaProfissional);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack){
        mRecyclerViewOnClickListenerHack = recyclerViewOnClickListenerHack;
    }

    public class AreaProfissionalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNome;
        private TextView mDescricao;

        public AreaProfissionalViewHolder(View itemView) {
            super(itemView);
            mNome = (TextView) itemView.findViewById(R.id.view_holder_text1);
            mDescricao = (TextView) itemView.findViewById(R.id.view_holder_text2);

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
