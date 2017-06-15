package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.model.Formacao;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoCursoComplementar extends SincronizacaoAbstract<CursoComplementar> {

    public SincronizacaoCursoComplementar(Context context) {
        super(context);
        Log.i("INFO", "Curso Complementar");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<CursoComplementar> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<CursoComplementar> cursosComplementares = dataBase.retrieve(CursoComplementar.class);

            if (cursosComplementares.isEmpty()) {
                return null;
            } else {
                Call<List<CursoComplementar>> cursosComplementaresWeb = retrofitInicializador.getCursoComplementaresService().salvar(cursosComplementares);
                return cursosComplementaresWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<CursoComplementar> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<CursoComplementar> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Cursos Complementares Nula");
        }

        for (CursoComplementar cursoComplementar : lista) {
            dataBase.persist(cursoComplementar);
        }

    }
}
