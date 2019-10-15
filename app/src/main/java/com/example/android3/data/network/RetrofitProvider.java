package com.example.android3.data.network;

import com.example.android3.data.models.retrofit.RepoInRetrofit;
import com.example.android3.data.models.retrofit.UserInRetrofit;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
public interface RetrofitProvider {
    @Headers({
            "Accept: application/vnd.github.v3+json",
            "UserInRetrofit-Agent: Android3"
    })
    @GET("users")
    Single<List<UserInRetrofit>> loadUsers();

    @Headers({
            "Accept: application/vnd.github.v3+json",
            "UserInRetrofit-Agent: Android3"
    })
    @GET("users/{user}")
    Single<UserInRetrofit> loadUser(@Path("user") String user);

    @Headers({
            "Accept: application/vnd.github.v3+json",
            "UserInRetrofit-Agent: Android3"
    })
    @GET("users/{user}/repos")
    Single<List<RepoInRetrofit>> loadRepos(@Path("user") String user);
}
