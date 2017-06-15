package tcc.utfpr.edu.br.soec.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by rodri on 08/02/2017.
 */

public class Prefs {

    public static final String PREF_ID = "SOEC";

    /**
     * Web Service
     */
    public static final String LINKWEBSERVICE = "http://192.168.1.4:8085/api/";

    /*
    * Boolean
    * */
    public static final String DATABASE = "DATABASE";
    public static final String CONTA_USUARIO_CRIADA = "CONTA_USUARIO_CRIADA";
    public static final String LEMBRAR_ME = "LEMBRAR_ME";
    public static final String USUARIO = "USUARIO";
    public static final String SENHA = "SENHA";
    public static final String CADASTRO_PESSOA = "CADASTRO_PESSOA";
    public static final String CANDIDATO = "CANDIDATO";
    public static final String CADASTRO_COMPLETO = "CADASTRO_COMPLETO";


    public static boolean getBoolean(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getBoolean(chave, false);
    }

    public static void setBoolean(Context context, String chave, boolean valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(chave, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getInt(chave, 0);
    }

    public static void setInteger(Context context, String chave, int valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(chave, valor);
        editor.commit();
    }

    public static String getString(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getString(chave, "");
    }

    public static void setString(Context context, String chave, String valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public static float getFloat(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getFloat(chave, 0);
    }

    public static void setFloat(Context context, String chave, float valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(chave, valor);
        editor.commit();
    }

    public static long getLong(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getLong(chave, 0);
    }

    public static void setLong(Context context, String chave, long valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(chave, valor);
        editor.commit();
    }

    public static Set<String> getStringSet(Context context, String chave) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        return pref.getStringSet(chave, null);
    }

    public static void setStringSet(Context context, String chave, Set<String> valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(chave, valor);
        editor.commit();
    }
}
