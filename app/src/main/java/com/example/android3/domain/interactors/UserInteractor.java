package com.example.android3.domain.interactors;

import com.example.android3.data.entities.User;
import com.example.android3.domain.repositories.UserRepo;

import io.reactivex.Single;

public class UserInteractor {
    private UserRepo user;

    public UserInteractor(UserRepo user) {
        this.user = user;
    }

    public Single<User> getUser(String name){
        return user.getUser(name);
    }
}
