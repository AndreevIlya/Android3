package com.example.android3.data.network;

import com.example.android3.data.models.Repo;
import com.example.android3.data.models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
public interface Api {
    @Headers({
            "Accept: application/vnd.github.v3+json",
            "User-Agent: Android3"
    })
    @GET("users")
    Single<List<User>> loadUsers();

    @Headers({
            "Accept: application/vnd.github.v3+json",
            "User-Agent: Android3"
    })
    @GET("users/{user}")
    Single<User> loadUser(@Path("user") String user);

    @Headers({
            "Accept: application/vnd.github.v3+json",
            "User-Agent: Android3"
    })
    @GET("users/{user}/repos")
    Single<List<Repo>> loadRepos(@Path("user") String user);
}
