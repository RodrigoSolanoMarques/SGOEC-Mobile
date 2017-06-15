package tcc.utfpr.edu.br.soec.sincronizacao.impl;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import retrofit2.Call;
import tcc.utfpr.edu.br.soec.dto.CargoDTO;
import tcc.utfpr.edu.br.soec.dto.CidadeDTO;
import tcc.utfpr.edu.br.soec.model.Cargo;
import tcc.utfpr.edu.br.soec.model.Cidade;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;
import tcc.utfpr.edu.br.soec.sincronizacao.SincronizacaoAbstract;


public class SincronizacaoCargo extends SincronizacaoAbstract<CargoDTO> {

    private List<CargoDTO> cargoDTOs;

    public SincronizacaoCargo(Context context) {
        super(context);
        Log.i("INFO", "Cargo");
    }

    @Override
    protected boolean isPost() {
        return false;
    }

    @Override
    protected List<CargoDTO> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador) {
        return null;
    }

    @Override
    protected List<CargoDTO> get(RetrofitInicializador retrofitInicializador) {
        Call<List<CargoDTO>> cargos = retrofitInicializador.getCargoService().listar();

        try {
            cargoDTOs = cargos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return cargoDTOs;
    }

    @Override
    protected void salvarSincronizacao(Jpdroid dataBase, List<CargoDTO> lista) throws Exception {

        if (lista == null) {
            throw new Exception("Lista de Cargo Nula");
        }

        if (lista.size() > 0) {
            dataBase.deleteAll(Cidade.class);
        }

        for (CargoDTO cargoDTO : lista) {
            Cargo cargo = new Cargo().converterCargoDTO(cargoDTO);
            dataBase.persist(cargo);
        }
    }
}
