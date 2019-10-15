package com.example.android3.data.models.sugar;

import com.example.android3.data.entities.Repo;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

@Table
public class RepoInSugar extends SugarRecord {
    private String name;//Primary key
    private String url;
    private String description;

    public RepoInSugar(){}

    public RepoInSugar(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public RepoInSugar(Repo repo){
        name = repo.getName();
        url = repo.getUrl();
        description = repo.getDescription();
    }

    public Repo convertToEntity(){
        return new Repo(name,url,description);
    }
}
