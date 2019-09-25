package com.example.android3.domain.interactors;

import com.example.android3.data.models.User;
import com.example.android3.domain.repositories.UserRepo;

import io.reactivex.Single;

public class UserInteractor {
    private UserRepo userRepo;

    public UserInteractor(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Single<User> getUser(String name){
        return userRepo.getUser(name);
    }
}
