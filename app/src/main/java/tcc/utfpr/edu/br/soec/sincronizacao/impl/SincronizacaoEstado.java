package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.dto.EstadoDTO;
import tcc.utfpr.edu.br.soec.model.Estado;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoEstado extends SincronizacaoAbstract<EstadoDTO> {

    private List<EstadoDTO> estadosDTOs;
    public SincronizacaoEstado(Context context) {
        super(context);
        Log.i("INFO", "Estado");
    }

    @Override
    protected boolean isPost() {
        return false;
    }

    @Override
    protected List<EstadoDTO> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected List<EstadoDTO> get(RetrofitInicializador retrofitInicializador) {
        Call<List<EstadoDTO>> estados = retrofitInicializador.getEstadoService().listar();
        try {
            estadosDTOs = estados.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return estadosDTOs;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<EstadoDTO> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Estado Nula");
        }

        if (lista.size() > 0) {
            dataBase.deleteAll(Estado.class);
        }

        for (EstadoDTO estadoDTO : lista) {
            Estado estado = new Estado().converterEstadoDTO(estadoDTO);
            dataBase.persist(estado);
        }
    }
}
