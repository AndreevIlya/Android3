package com.example.android3.domain.interactors;

import com.example.android3.data.models.User;
import com.example.android3.domain.repositories.UsersRepo;

import java.util.List;

import io.reactivex.Single;

public class UsersInteractor {
    private UsersRepo usersRepo;

    public UsersInteractor(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Single<List<User>> getUsers(){
        return usersRepo.getUsers();
    }
}
