package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.Curriculo;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoCurriculo extends SincronizacaoAbstract<Curriculo> {

    public SincronizacaoCurriculo(Context context) {
        super(context);
        Log.i("INFO", "Curriculo");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<Curriculo> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<Curriculo> curriculos = dataBase.retrieve(Curriculo.class);

            if (curriculos.isEmpty()) {
                return null;
            } else {
                Call<List<Curriculo>> cursosComplementaresWeb = retrofitInicializador.getCurriculolService().salvar(curriculos);
                return cursosComplementaresWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Curriculo> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<Curriculo> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Curriculo Nula");
        }

        for (Curriculo curriculo : lista) {
            dataBase.persist(curriculo);
        }

    }
}
