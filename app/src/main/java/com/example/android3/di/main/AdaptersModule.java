package com.example.android3.di.main;

import com.example.android3.presentation.adapters.ReposAdapter;
import com.example.android3.presentation.adapters.UsersAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public
class AdaptersModule {

    @Provides
    @MainScope
    UsersAdapter provideUsersAdapter(){
        return new UsersAdapter();
    }

    @Provides
    @MainScope
    ReposAdapter provideReposAdapter(){
        return new ReposAdapter();
    }
}
