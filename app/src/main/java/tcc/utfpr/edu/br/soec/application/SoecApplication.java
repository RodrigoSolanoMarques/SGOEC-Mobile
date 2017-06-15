package tcc.utfpr.edu.br.soec.application;

import android.app.Application;

import br.com.rafael.jpdroid.core.Jpdroid;

/**
 * Created by rodri on 14/05/2017.
 */

public class SoecApplication extends Application {

    private static SoecApplication singleton;

    private static Jpdroid dataBase;

    public static SoecApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        dataBase = Jpdroid.getInstance();
        dataBase.setContext(this);
    }

    public Jpdroid dataBase(){
        return dataBase;
    }
}
