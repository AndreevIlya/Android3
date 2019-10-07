package com.example.android3.domain.repositories;

import com.example.android3.data.entities.User;

import java.util.List;

import io.reactivex.Single;

public interface Users {

    Single<List<User>> getUsers();
}
