package com.example.android3.di.main;

import com.example.android3.di.app.AppComponent;
import com.example.android3.presentation.main.MainActivity;

import dagger.Component;

@Component(modules = {InteractorsModule.class,
        AdaptersModule.class,
        ViewModelModule.class
},dependencies = AppComponent.class)
@MainScope
public interface MainComponent {

    void inject(MainActivity act);
}
