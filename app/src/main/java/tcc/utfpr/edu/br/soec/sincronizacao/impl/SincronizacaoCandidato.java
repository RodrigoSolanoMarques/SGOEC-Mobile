package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.Candidato;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoCandidato extends SincronizacaoAbstract<Candidato> {

    public SincronizacaoCandidato(Context context) {
        super(context);
        Log.i("INFO", "Candidato");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<Candidato> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<Candidato> candidatos = dataBase.retrieve(Candidato.class, true);

            if (candidatos.isEmpty()) {
                return new ArrayList<>();
            } else {
                Call<List<Candidato>> contaUsuarioWeb = retrofitInicializador.getCandidatoService().salvar(candidatos);
                return contaUsuarioWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Candidato> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<Candidato> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Candidato Nula");
        }

        for (Candidato candidato : lista) {
            dataBase.persist(candidato);
        }

    }
}
