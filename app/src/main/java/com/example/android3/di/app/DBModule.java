package com.example.android3.di.app;

import com.example.android3.data.database.DBProvider;
import com.example.android3.data.database.RepoSugarImpl;
import com.example.android3.data.database.UserSugarImpl;
import com.example.android3.data.entities.Repo;
import com.example.android3.data.entities.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    @Provides
    @Singleton
    DBProvider<User,User> provideUserSugarDB(){
        return new UserSugarImpl();
    }

    @Provides
    @Singleton
    DBProvider<Repo,Repo> provideRepoSugarDB(){
        return new RepoSugarImpl();
    }
}
