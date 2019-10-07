package com.example.android3.domain.interactors;

import com.example.android3.data.entities.User;
import com.example.android3.domain.repositories.Users;

import java.util.List;

import io.reactivex.Single;

public class UsersInteractor {
    private Users users;

    public UsersInteractor(Users users) {
        this.users = users;
    }

    public Single<List<User>> getUsers(){
        return users.getUsers();
    }
}
