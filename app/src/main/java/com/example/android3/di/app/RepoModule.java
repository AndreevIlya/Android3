package com.example.android3.di.app;

import com.example.android3.data.database.DBProvider;
import com.example.android3.data.entities.Repo;
import com.example.android3.data.entities.User;
import com.example.android3.data.network.RetrofitProvider;
import com.example.android3.data.repositories.ReposImpl;
import com.example.android3.data.repositories.UserImpl;
import com.example.android3.data.repositories.UsersImpl;
import com.example.android3.domain.repositories.Repos;
import com.example.android3.domain.repositories.UserRepo;
import com.example.android3.domain.repositories.Users;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    @Singleton
    UserRepo provideUserRepo(RetrofitProvider rp, DBProvider<User,User> dbp){
        return new UserImpl(rp,dbp);
    }

    @Provides
    @Singleton
    Repos provideReposRepo(RetrofitProvider rp, DBProvider<Repo, Repo> dbp){
        return new ReposImpl(rp,dbp);
    }

    @Provides
    @Singleton
    Users provideUsersRepo(RetrofitProvider rp, DBProvider<User,User> dbp){
        return new UsersImpl(rp,dbp);
    }
}
