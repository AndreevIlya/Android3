package com.example.android3.presentation.main;

import android.app.Application;

import com.orm.SugarContext;

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
