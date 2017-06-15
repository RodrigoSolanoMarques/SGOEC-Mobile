package tcc.utfpr.edu.br.soec.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexaoUtils {
    private static int TIMEOUT = 5000;

    public static boolean temConexao(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static String formatarURLConexao(String url){
        return "http://" + Prefs.LINKWEBSERVICE + url;
    }

    public static InputStream get(String urlConexao) throws Exception {
        InputStream inputStream = null;
        try {
            URL url = new URL(formatarURLConexao(urlConexao));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(TIMEOUT);
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Method", "GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(false);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = urlConnection.getInputStream();
            }
        } catch (Exception ex){
            inputStream = null;
            throw new Exception(ex.getMessage());
        }
        return inputStream;
    }

    public static InputStream post(String urlConexao, String body) throws Exception{
        InputStream inputStream = null;
        try {
            URL url = new URL(formatarURLConexao(urlConexao));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(TIMEOUT);
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Method", "POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(body.toString().getBytes("UTF-8"));
            outputStream.close();

            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = urlConnection.getInputStream();
            }
        } catch (Exception ex){
            inputStream = null;
            throw new Exception(ex.getMessage());
        }
        return inputStream;
    }

}
