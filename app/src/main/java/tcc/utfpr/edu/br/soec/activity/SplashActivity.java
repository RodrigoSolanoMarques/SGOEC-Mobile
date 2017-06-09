package tcc.utfpr.edu.br.soec.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.asynctask.CriarBancoDadosAsyncTask;
import tcc.utfpr.edu.br.soec.utils.Prefs;


public class SplashActivity extends AppCompatActivity {
    private TextView activity_splash_mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        activity_splash_mensagem = (TextView) findViewById(R.id.activity_splash_mensagem);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Prefs.getBoolean(this, Prefs.DATABASE) || !Prefs.getBoolean(this, Prefs.LEMBRAR_ME ) ){
            CriarBancoDadosAsyncTask criarBancoDados = new CriarBancoDadosAsyncTask(this, activity_splash_mensagem, LoginActivity.class);
            criarBancoDados.execute();
            return;
        }

        CriarBancoDadosAsyncTask criarBancoDados = new CriarBancoDadosAsyncTask(this, activity_splash_mensagem, MainActivity.class);
        criarBancoDados.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
