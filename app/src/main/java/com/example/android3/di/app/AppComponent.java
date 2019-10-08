package com.example.android3.di.app;

import com.example.android3.domain.repositories.Repos;
import com.example.android3.domain.repositories.UserRepo;
import com.example.android3.domain.repositories.Users;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RetrofitModule.class,
        DBModule.class,
        RepoModule.class
})
@Singleton
public interface AppComponent {

    UserRepo provideUserRepo();
    Users provideUsersRepo();
    Repos provideReposRepo();
}
