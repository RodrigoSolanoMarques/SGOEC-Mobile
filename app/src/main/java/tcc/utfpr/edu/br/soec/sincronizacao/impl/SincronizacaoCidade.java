package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.dto.CidadeDTO;
import tcc.utfpr.edu.br.soec.model.Cidade;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoCidade extends SincronizacaoAbstract<CidadeDTO> {

    private List<CidadeDTO> cidadeDTOs;

    public SincronizacaoCidade(Context context) {
        super(context);
        Log.i("INFO", "Cidade");
    }

    @Override
    protected boolean isPost() {
        return false;
    }

    @Override
    protected List<CidadeDTO> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected List<CidadeDTO> get(RetrofitInicializador retrofitInicializador) {
        Call<List<CidadeDTO>> cidades = retrofitInicializador.getCidadeService().listar();

        try {
            cidadeDTOs = cidades.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return cidadeDTOs;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<CidadeDTO> lista) throws Exception {
        if (lista == null) {
            throw new Exception("Lista de Cidade Nula");
        }

        if (lista.size() > 0) {
            dataBase.deleteAll(Cidade.class);
        }

        for (CidadeDTO cidadeDTO : lista) {
            Cidade cidade = new Cidade().converterCidadeDTO(cidadeDTO);
            dataBase.persist(cidade);
        }
    }
}
