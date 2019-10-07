package com.example.android3.data.models.retrofit;

import com.example.android3.data.entities.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class UserInRetrofit {
    @SerializedName("login")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("html_url")
    @Expose
    private String url;

    public UserInRetrofit(){}

    public UserInRetrofit(User user){
        name = user.getName();
        id = user.getId();
        url = user.getUrl();
    }

    public User convertToEntity(){
        return new User(name,id,url);
    }
}
