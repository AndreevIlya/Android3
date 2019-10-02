package com.example.android3.domain.interactors;

import com.example.android3.data.entities.Repo;
import com.example.android3.domain.repositories.Repos;

import java.util.List;

import io.reactivex.Single;

public class ReposInteractor {
    private Repos repos;

    public ReposInteractor(Repos repos) {
        this.repos = repos;
    }

    public Single<List<Repo>> getRepos(String name){
        return repos.getRepos(name);
    }
}
