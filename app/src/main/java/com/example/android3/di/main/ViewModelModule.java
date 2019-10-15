package com.example.android3.di.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;
import com.example.android3.presentation.Factories.MainViewModelFactory;
import com.example.android3.presentation.main.MainActivity;
import com.example.android3.presentation.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    private AppCompatActivity activity;

    public ViewModelModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    MainViewModelFactory provideMainViewModelFactory(UserInteractor ui, UsersInteractor usi, ReposInteractor ri) {
        return new MainViewModelFactory(ui, usi, ri, MainActivity.NAME);
    }

    @Provides
    @MainScope
    MainViewModel provideMainViewModel(MainViewModelFactory factory){
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }
}
