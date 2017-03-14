package tcc.utfpr.edu.br.soec.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.activity.MainActivity;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.utils.Prefs;

/**
 * Created by rodri on 07/02/2017.
 */

public class CriarBancoDadosAsyncTask extends AsyncTask<Void, String, Void> {

    private ProgressDialog progressDialog;
    private Jpdroid database;
    private Context context;
    private TextView textView;
    private Class aClass;

    public CriarBancoDadosAsyncTask(Context context) {
        this.context = context;
    }

    public CriarBancoDadosAsyncTask(Context context, Class aClass) {
        this.context = context;
        this.aClass = aClass;
    }

    public CriarBancoDadosAsyncTask(Context context, TextView textView, Class aClass) {
        this.context = context;
        this.aClass = aClass;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (textView == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Aguarde");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        database = Jpdroid.getInstance();
        database.setContext(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            if(!Prefs.getBoolean(context, Prefs.DATABASE)){
                publishProgress("Criando o Banco de Dados");
                database.addEntity(ContaUsuario.class);
                database.open();

                Prefs.setBoolean(context, Prefs.DATABASE, true);
                publishProgress("Banco de Dados Criado");
            }

            new Thread().sleep(2000);

            publishProgress("Atualizando Banco de Dados");

            new Thread().sleep(3000);

            publishProgress("Banco de Dados Atualizado");

            new Thread().sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
            Prefs.setBoolean(context, Prefs.DATABASE, false);
            publishProgress("Não Foi Possível Criar o Banco de Dados");
            if (textView != null) {
                progressDialog.dismiss();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (textView != null) {
            textView.setText(values[0]);
        } else {
            //Atualiza a mensagem do ProgressDialog
            this.progressDialog.setMessage(values[0]);
        }
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        database.close();

        if (aClass != null) {
            Intent intent = new Intent(context, aClass);
            context.startActivity(intent);
        }

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }
}
