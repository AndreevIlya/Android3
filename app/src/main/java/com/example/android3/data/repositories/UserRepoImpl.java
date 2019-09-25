package com.example.android3.data.repositories;

import com.example.android3.data.models.User;
import com.example.android3.data.network.Api;
import com.example.android3.domain.repositories.UserRepo;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepoImpl implements UserRepo {
    private Api api;

    public UserRepoImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<User> getUser(String name) {
        return api.loadUser(name)
                .subscribeOn(Schedulers.io());
    }
}
