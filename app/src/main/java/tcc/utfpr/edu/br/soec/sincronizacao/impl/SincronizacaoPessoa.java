package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoPessoa extends SincronizacaoAbstract<Pessoa> {

    public SincronizacaoPessoa(Context context) {
        super(context);
        Log.i("INFO", "Pessoa");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<Pessoa> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<Pessoa> pessoas = dataBase.retrieve(Pessoa.class, true);

            if (pessoas.isEmpty()) {
                return null;
            } else {
                Call<List<Pessoa>> pessoasWeb = retrofitInicializador.getPessoaService().salvar(pessoas);
                return pessoasWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Pessoa> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<Pessoa> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Pessoas Nula");
        }

        for (Pessoa pessoa : lista) {
            dataBase.persist(pessoa);
        }

    }
}
