package com.example.android3.data.repositories;

import com.example.android3.data.models.User;
import com.example.android3.data.network.Api;
import com.example.android3.domain.repositories.UsersRepo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UsersRepoImpl implements UsersRepo {

    private Api api;

    public UsersRepoImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<List<User>> getUsers() {
        return api.loadUsers()
                .subscribeOn(Schedulers.io());
    }
}
