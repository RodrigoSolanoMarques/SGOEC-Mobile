package tcc.utfpr.edu.br.soec.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.rafael.jpdroid.core.Jpdroid;
import br.com.rafael.jpdroid.exceptions.JpdroidException;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.utils.Prefs;
import tcc.utfpr.edu.br.soec.utils.ToastUtils;

public class ContaUsuarioActivity extends AppCompatActivity {

    private Context context = this;

    private EditText activity_conta_usuario_user_name;
    private EditText activity_conta_usuario_email;
    private EditText activity_conta_usuario_senha;
    private EditText activity_conta_usuario_senha_novamente;
    private CheckBox activity_conta_usuario_lembre_me;

    /* Banco de dados */
    private Jpdroid dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_usuario);

        /* Recuperação dos campos */
        activity_conta_usuario_user_name = (EditText) findViewById(R.id.activity_conta_usuario_user_name);
        activity_conta_usuario_email = (EditText) findViewById(R.id.activity_conta_usuario_email);
        activity_conta_usuario_senha = (EditText) findViewById(R.id.activity_conta_usuario_senha);
        activity_conta_usuario_senha_novamente = (EditText) findViewById(R.id.activity_conta_usuario_senha_novamente);
        activity_conta_usuario_lembre_me = (CheckBox) findViewById(R.id.activity_conta_usuario_lembre_me);

        /*
        * Instância o banco para poder trabalhar com ele
        * */
        dataBase = Jpdroid.getInstance();
        dataBase.setContext(context);
        dataBase.open();

    }

    public void activityContaUsuarioCadastrarOnClick(View view){
        /*
        * Salva conta de usuário no banco
        * */
        try {
            ContaUsuario contaUsuario =  new ContaUsuario();
            contaUsuario.setUsername(activity_conta_usuario_user_name.getText().toString());
            contaUsuario.setEmail(activity_conta_usuario_email.getText().toString());

            /*
            * Validação de senha
            * */
            if(!activity_conta_usuario_senha.getText().toString().equals(activity_conta_usuario_senha_novamente.getText().toString())){
                ToastUtils.setMsgLong(context, getResources().getString(R.string.senhas_diferentes));
                return;
            }

            // Falta criptografar a senha
            contaUsuario.setSenha(activity_conta_usuario_senha.getText().toString());

            /*
            * Persiste no banco a conta de usuário
            * */
            dataBase.persist(contaUsuario);

            if(activity_conta_usuario_lembre_me.isChecked()){
                Prefs.setBoolean(context, Prefs.LEMBRAR_ME, true);
                Prefs.setString(context, Prefs.USUARIO, contaUsuario.getUsername());
                Prefs.setString(context, Prefs.SENHA, contaUsuario.getSenha());
            }else{
                Prefs.setBoolean(context, Prefs.LEMBRAR_ME, false);
                Prefs.setString(context, Prefs.USUARIO, null);
                Prefs.setString(context, Prefs.SENHA, null);
            }

            /**
             * Cria parametro no SharedPreference.
             * Indica que uma conta já foi criada.
             * Prefs.CONTAUSUARIO = true
             */
            Prefs.setBoolean(context, Prefs.CONTA_USUARIO_CRIADA, true);

            /*
            * Chama a tela de login
            * */
            Intent intent = new Intent(ContaUsuarioActivity.this, MainActivity.class);
            startActivity(intent);

            /**
             * Informa o usuário que a conta foi criado com sucesso
             */
            ToastUtils.setMsgShort(context, getResources().getString(R.string.conta_usuario_criada_com_sucesso));


            Cursor cursor = dataBase.createQuery(ContaUsuario.class);
            if(cursor.moveToFirst()){
                ToastUtils.setMsgShort(context, cursor.getString(cursor.getColumnIndex("username")));
            }


            /**
             * Finaliza activity
             */

            finish();
        } catch (JpdroidException e) {
            e.printStackTrace();
            ToastUtils.setMsgShort(context, getResources().getString(R.string.erro_criar_conta_usuario));
        }


    }

    public void activityContaUsuarioCancelarOnClick(View view){
        onBackPressed();
    }

}
