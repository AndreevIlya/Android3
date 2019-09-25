package com.example.android3.domain.repositories;

import com.example.android3.data.models.User;

import java.util.List;

import io.reactivex.Single;

public interface UsersRepo {

    Single<List<User>> getUsers();
}
