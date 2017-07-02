package tcc.utfpr.edu.br.soec.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import tcc.utfpr.edu.br.soec.sincronizacao.ListaSincronizacao;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

/**
 * Created by rodri on 29/06/2017.
 */

public class SincronizacaoAsyncTask extends AsyncTask<Void, String, Void> {

    private Context context;
    private ProgressDialog progressDialog;

    public SincronizacaoAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Aguarde");
        progressDialog.setMessage("Sincronizando");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            new ListaSincronizacao(false).sincronizarTudo(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        ToastUtils.setMsgLong(context,"Sincronização Finalizada");
    }
}
