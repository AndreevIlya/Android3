package com.example.android3.presentation.main;

import android.app.Application;

import com.example.android3.di.app.AppComponent;
import com.example.android3.di.app.DBModule;
import com.example.android3.di.app.DaggerAppComponent;
import com.example.android3.di.app.RepoModule;
import com.example.android3.di.app.RetrofitModule;
import com.orm.SugarContext;

public class MainApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);

        appComponent = DaggerAppComponent
                .builder()
                .retrofitModule(new RetrofitModule())
                .dBModule(new DBModule())
                .repoModule(new RepoModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
