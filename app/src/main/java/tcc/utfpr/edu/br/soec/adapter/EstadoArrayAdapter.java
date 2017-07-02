package tcc.utfpr.edu.br.soec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tcc.utfpr.edu.br.soec.model.Estado;

/**
 * Created by rodri on 01/07/2017.
 */

public class EstadoArrayAdapter extends ArrayAdapter<Estado> {

    private Context context;
    private List<Estado> estados;

    public EstadoArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Estado> estados) {
        super(context, resource, estados);
        this.context = context;
        this.estados = estados;
    }

    public int getCount(){
        return estados.size();
    }



    public Estado getItem(int position){
        return estados.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(estados.get(position).getNome());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(estados.get(position).getNome());
        label.setPadding(20,20,20,20);
        return label;
    }
}
