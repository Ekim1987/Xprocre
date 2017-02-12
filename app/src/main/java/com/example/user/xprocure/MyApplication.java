package com.example.user.xprocure;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 07/02/2017.
 */

public class MyApplication extends Application {

    private  static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }
    public  static MyApplication getsInstance(){
        return sInstance;
    }
    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
