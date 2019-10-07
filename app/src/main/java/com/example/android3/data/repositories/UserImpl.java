package com.example.android3.data.repositories;

import com.example.android3.data.database.DBProvider;
import com.example.android3.data.entities.User;
import com.example.android3.data.models.retrofit.UserInRetrofit;
import com.example.android3.data.network.RetrofitProvider;
import com.example.android3.domain.repositories.UserRepo;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserImpl implements UserRepo {
    private RetrofitProvider retrofitProvider;
    private DBProvider<User,User> userSugarDB;

    public UserImpl(RetrofitProvider retrofitProvider,
                    DBProvider<User, User> userSugarDB) {
        this.retrofitProvider = retrofitProvider;
        this.userSugarDB = userSugarDB;
    }

    @Override
    public Single<User> getUser(String name) {
        return retrofitProvider.loadUser(name)
                .subscribeOn(Schedulers.io())
                .map(UserInRetrofit::convertToEntity)
                .doOnSuccess(user -> userSugarDB.insert(user));
    }
}
