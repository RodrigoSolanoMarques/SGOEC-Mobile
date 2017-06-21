package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.model.Pessoa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoContaUsuario extends SincronizacaoAbstract<ContaUsuario> {

    public SincronizacaoContaUsuario(Context context) {
        super(context);
        Log.i("INFO", "Conta Usuario");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<ContaUsuario> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<ContaUsuario> contasUsuarios = dataBase.retrieve(ContaUsuario.class, true);

            if (contasUsuarios.isEmpty()) {
                return null;
            } else {
                Call<List<ContaUsuario>> contaUsuarioWeb = retrofitInicializador.getContaUsuarioService().salvar(contasUsuarios);
                return contaUsuarioWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<ContaUsuario> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<ContaUsuario> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Conta Usuario Nula");
        }

        for (ContaUsuario contaUsuario : lista) {
            dataBase.persist(contaUsuario);
        }

    }
}
