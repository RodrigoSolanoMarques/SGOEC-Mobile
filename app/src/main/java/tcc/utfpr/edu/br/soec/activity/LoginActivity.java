package tcc.utfpr.edu.br.soec.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.rafael.jpdroid.core.Jpdroid;
import tcc.utfpr.edu.br.soec.R;
import tcc.utfpr.edu.br.soec.application.SoecApplication;
import tcc.utfpr.edu.br.soec.model.ContaUsuario;
import tcc.utfpr.edu.br.soec.utils.Prefs;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Button activity_login_btn_cadastrar_me;
    private EditText activity_login_username;
    private EditText activity_login_senha;
    private CheckBox activity_login_lembre_me;

    private Context context = this;
    private Cursor cursor;
    private Jpdroid dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity_login_username = (EditText) findViewById(R.id.activity_login_username);
        activity_login_senha = (EditText) findViewById(R.id.activity_login_senha);
        activity_login_btn_cadastrar_me = (Button) findViewById(R.id.activity_login_btn_cadastrar_me);
        activity_login_lembre_me = (CheckBox) findViewById(R.id.activity_login_lembre_me);

        SoecApplication applicationContext = (SoecApplication) getApplication();

        dataBase = applicationContext.dataBase();
        dataBase.open();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Prefs.getBoolean(context, Prefs.CONTA_USUARIO_CRIADA)) {
            activity_login_btn_cadastrar_me.setVisibility(View.INVISIBLE);
            return;
        }
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mUserNameView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void fazerLoginOnClick(View view) {
        // Reset errors.
        activity_login_username.setError(null);
        activity_login_senha.setError(null);

        String username = activity_login_username.getText().toString();
        String password = activity_login_senha.getText().toString();
        String nomeUsuario = "";
        String senha = "";

        cursor = dataBase.createQuery(ContaUsuario.class);
        if (cursor.moveToFirst()) {
            nomeUsuario = cursor.getString(cursor.getColumnIndex("username"));
            senha = cursor.getString(cursor.getColumnIndex("senha"));
        }

        boolean cancel = false;
        View focusView = null;

        //  COMEÇA AS VALIDAÇÕES

        if (senha == null || !password.equals(senha)) {
            activity_login_senha.setError(getString(R.string.erro_senha_incorreta));
            focusView = activity_login_senha;
            cancel = true;
        }

        if (nomeUsuario == null || !username.equals(nomeUsuario)) {
            activity_login_username.setError(getString(R.string.erro_nome_usuario_incorreto));
            focusView = activity_login_username;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            activity_login_senha.setError(getString(R.string.erro_senha_invalida));
            focusView = activity_login_senha;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            activity_login_username.setError(getString(R.string.error_campo_obrigatorio));
            focusView = activity_login_username;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return;
        }

        if (activity_login_lembre_me.isSelected()) {
            Prefs.setBoolean(context, Prefs.LEMBRAR_ME, true);
        } else {
            Prefs.setBoolean(context, Prefs.LEMBRAR_ME, false);
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    public void cadastrarContaUsuarioOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, ContaUsuarioActivity.class);
        startActivity(intent);
    }


}

