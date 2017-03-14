package tcc.utfpr.edu.br.soec.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rodri on 13/02/2017.
 */

public class ToastUtils {

    public static void setMsgLong(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public static void setMsgShort(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
