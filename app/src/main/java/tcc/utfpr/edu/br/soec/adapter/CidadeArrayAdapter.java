package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import tcc.utfpr.edu.br.soec.model.Cidade;

/**
 * Created by rodri on 01/07/2017.
 */

public class CidadeArrayAdapter extends ArrayAdapter<Cidade> {

    private Context context;
    private List<Cidade> cidades;

    public CidadeArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Cidade> cidades) {
        super(context, resource, cidades);
        this.context = context;
        this.cidades = cidades;
    }

    public int getCount(){
        return cidades.size();
    }

    public Cidade getItem(int position){
        return cidades.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(cidades.get(position).getNome());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(cidades.get(position).getNome());
        label.setPadding(20,20,20,20);
        return label;
    }
}
