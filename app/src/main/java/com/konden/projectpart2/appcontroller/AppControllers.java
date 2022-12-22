package com.konden.projectpart2.appcontroller;
import android.app.Application;

import com.yariksoffice.lingver.Lingver;

import java.util.Locale;


public class AppControllers extends Application {
    private static AppControllers instance;

    @Override

    public void onCreate() {
        super.onCreate();
        instance = this;
        Lingver.init(this, Locale.getDefault());

    }

    public static AppControllers getInstance()   {
        return instance;
    }
}