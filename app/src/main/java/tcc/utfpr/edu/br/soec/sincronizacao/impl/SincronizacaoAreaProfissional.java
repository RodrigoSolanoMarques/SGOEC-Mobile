package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.dto.AreaProfissionalDTO;
import tcc.utfpr.edu.br.soec.dto.EstadoDTO;
import tcc.utfpr.edu.br.soec.model.AreaProfissional;
import tcc.utfpr.edu.br.soec.model.Estado;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoAreaProfissional extends SincronizacaoAbstract<AreaProfissionalDTO> {

    private List<AreaProfissionalDTO> areaProfissionalDTOs;

    public SincronizacaoAreaProfissional(Context context) {
        super(context);
        Log.i("INFO", "Area Profissional");
    }

    @Override
    protected boolean isPost() {
        return false;
    }

    @Override
    protected List<AreaProfissionalDTO> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected List<AreaProfissionalDTO> get(RetrofitInicializador retrofitInicializador) {
        Call<List<AreaProfissionalDTO>> estados = retrofitInicializador.getAreaProfissionalService().listar();

        try {
            areaProfissionalDTOs = estados.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return areaProfissionalDTOs;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<AreaProfissionalDTO> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Area Profissional Nula");
        }

        if (lista.size() > 0) {
            dataBase.deleteAll(Estado.class);
        }

        for (AreaProfissionalDTO areaProfissionalDTO : lista) {
            AreaProfissional areaProfissional = new AreaProfissional().converterAreaProfissionalDTO(areaProfissionalDTO);
            dataBase.persist(areaProfissional);
        }
    }
}
