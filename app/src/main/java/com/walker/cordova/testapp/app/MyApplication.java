package com.walker.cordova.testapp.app;

import android.app.Application;

public class MyApplication extends Application {

    static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
