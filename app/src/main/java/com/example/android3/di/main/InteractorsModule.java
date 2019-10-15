package com.example.android3.di.main;

import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;
import com.example.android3.domain.repositories.Repos;
import com.example.android3.domain.repositories.UserRepo;
import com.example.android3.domain.repositories.Users;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorsModule {

    @Provides
    @MainScope
    UserInteractor provideUserInteractor(UserRepo user){
        return new UserInteractor(user);
    }

    @Provides
    @MainScope
    UsersInteractor provideUsersInteractor(Users users){
        return new UsersInteractor(users);
    }

    @Provides
    @MainScope
    ReposInteractor provideReposInteractor(Repos repos){
        return new ReposInteractor(repos);
    }
}
