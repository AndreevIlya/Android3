package com.example.android3.domain.repositories;

import com.example.android3.data.entities.User;

import io.reactivex.Single;

public interface UserRepo {
    Single<User> getUser(String name);
}
