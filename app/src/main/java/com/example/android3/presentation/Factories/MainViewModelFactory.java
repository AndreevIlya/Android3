package com.example.android3.presentation.Factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;
import com.example.android3.presentation.main.MainViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory  {
    private UserInteractor ui;
    private UsersInteractor usi;
    private ReposInteractor ri;
    private String name;

    public MainViewModelFactory(UserInteractor ui, UsersInteractor usi, ReposInteractor ri, String name) {
        this.ui = ui;
        this.usi = usi;
        this.ri = ri;
        this.name = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class)
        return (T) new MainViewModel(ui,usi,ri,name);
        throw new RuntimeException("Error while creating a VM factory.");
    }
}
