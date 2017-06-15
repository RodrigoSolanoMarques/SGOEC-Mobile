package tcc.utfpr.edu.br.soec.sincronizacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.retrofit.RetrofitInicializador;


public abstract class SincronizacaoAbstract<T> {

    private RetrofitInicializador retrofitInicializador;
    private Jpdroid dataBase;

    protected abstract boolean isPost();

    protected abstract List<T> post(Jpdroid dataBase, RetrofitInicializador retrofitInicializador);

    protected abstract List<T> get(RetrofitInicializador retrofitInicializador);

    protected abstract void salvarSincronizacao(Jpdroid dataBase, List<T> lista) throws Exception;

    public SincronizacaoAbstract(Context context) {
        dataBase = Jpdroid.getInstance();
        dataBase.setContext(context);
        this.retrofitInicializador = new RetrofitInicializador();
    }

    public void sincronizar() throws Exception {
        try {
            if (isPost()) {
                salvarSincronizacao(dataBase, post(dataBase, retrofitInicializador));
            } else {
                salvarSincronizacao(dataBase, get(retrofitInicializador));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}