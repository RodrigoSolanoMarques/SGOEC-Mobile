package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.utfpr.edu.br.soec.dto.FormacaoDTO;
import tcc.utfpr.edu.br.soec.model.Estado;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoFormacao extends SincronizacaoAbstract<Formacao> {

    public SincronizacaoFormacao(Context context) {
        super(context);
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<Formacao> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
         try {

            List<Formacao> formacoes = dataBase.retrieve(Formacao.class);

            if (formacoes.isEmpty()) {
                return null;
            } else {
                Call<List<Formacao>> formacaoesWeb = retrofitInicializador.getFormacaoService().salvar(formacoes);
                return formacaoesWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Formacao> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<Formacao> lista) {
        try {

            if (lista == null) {
                throw new Exception("Lista de Formacao Nula");
            }

            for (Formacao formacao : lista) {
                dataBase.persist(formacao);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
