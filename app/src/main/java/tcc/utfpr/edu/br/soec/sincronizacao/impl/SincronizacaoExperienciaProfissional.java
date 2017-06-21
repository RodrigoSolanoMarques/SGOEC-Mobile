package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.model.CursoComplementar;
import tcc.utfpr.edu.br.soec.model.ExperienciaProfissional;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoExperienciaProfissional extends SincronizacaoAbstract<ExperienciaProfissional> {

    public SincronizacaoExperienciaProfissional(Context context) {
        super(context);
        Log.i("INFO", "Experiencia profissional");
    }

    @Override
    protected boolean isPost() {
        return true;
    }

    @Override
    protected List<ExperienciaProfissional> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        try {

            List<ExperienciaProfissional> experienciaProfissionals = dataBase.retrieve(ExperienciaProfissional.class);

            if (experienciaProfissionals.isEmpty()) {
                return new ArrayList<>();
            } else {
                Call<List<ExperienciaProfissional>> cursosComplementaresWeb = retrofitInicializador.getExperienciaprofissionalService().salvar(experienciaProfissionals);
                return cursosComplementaresWeb.execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<ExperienciaProfissional> get(RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<ExperienciaProfissional> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Experiencias Profissionais Nula");
        }

        for (ExperienciaProfissional experienciaProfissional : lista) {
            dataBase.persist(experienciaProfissional);
        }

    }
}
