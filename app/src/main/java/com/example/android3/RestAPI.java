package com.example.android3;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface RestAPI {
    @GET("users")
    Call<List<RetrofitModel>> loadUsers();

    @GET("users/{user}")
    Call<List<RetrofitModel>> loadUser(@Path("user") String user);
}
