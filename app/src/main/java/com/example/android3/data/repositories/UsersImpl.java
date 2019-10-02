package com.example.android3.data.repositories;

import com.example.android3.data.database.DBProvider;
import com.example.android3.data.entities.User;
import com.example.android3.data.models.retrofit.UserInRetrofit;
import com.example.android3.data.network.RetrofitProvider;
import com.example.android3.domain.repositories.Users;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UsersImpl implements Users {

    private RetrofitProvider retrofitProvider;
    private DBProvider<User,User> userSugarDB;

    public UsersImpl(RetrofitProvider retrofitProvider, DBProvider<User, User> userSugarDB) {
        this.retrofitProvider = retrofitProvider;
        this.userSugarDB = userSugarDB;
    }

    @Override
    public Single<List<User>> getUsers() {
        return retrofitProvider.loadUsers()
                .subscribeOn(Schedulers.io())
                .map(uirs -> {
                    List<User> users = new ArrayList<>();
                    for(UserInRetrofit uir : uirs){
                        users.add(uir.convertToEntity());
                    }
                    return users;
                })
                .doOnSuccess(users -> {
                    for(User user : users){
                        userSugarDB.insert(user);
                    }
                });
    }
}
