package com.example.android3.data.models.retrofit;

import com.example.android3.data.entities.Repo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoInRetrofit {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("html_url")
    @Expose
    private String url;

    @SerializedName("description")
    @Expose
    private String description;

    public RepoInRetrofit(Repo repo){
        name = repo.getName();
        url = repo.getUrl();
        description = repo.getDescription();
    }

    public Repo convertToEntity(){
        return new Repo(name,url,description);
    }
}
