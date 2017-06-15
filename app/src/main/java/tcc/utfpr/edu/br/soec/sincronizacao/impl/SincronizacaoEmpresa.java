package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.dto.CidadeDTO;
import tcc.utfpr.edu.br.soec.dto.EmpresaDTO;
import tcc.utfpr.edu.br.soec.model.Cidade;
import tcc.utfpr.edu.br.soec.model.Empresa;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoEmpresa extends SincronizacaoAbstract<EmpresaDTO> {

    private List<EmpresaDTO> empresaDTOs;

    public SincronizacaoEmpresa(Context context) {
        super(context);
        Log.i("INFO", "Empresa");
    }

    @Override
    protected boolean isPost() {
        return false;
    }

    @Override
    protected List<EmpresaDTO> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected List<EmpresaDTO> get(RetrofitInicializador retrofitInicializador) {
        Call<List<EmpresaDTO>> empresas = retrofitInicializador.getEmpresaService().listar();

        try {
            empresaDTOs = empresas.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return empresaDTOs;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<EmpresaDTO> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Empresa Nula");
        }

        if (lista.size() > 0) {
            dataBase.deleteAll(Empresa.class);
        }

        for (EmpresaDTO empresaDTO : lista) {
            Empresa empresa = new Empresa().converterEmpresaDTO(empresaDTO);
            dataBase.persist(empresa);
        }
    }
}
