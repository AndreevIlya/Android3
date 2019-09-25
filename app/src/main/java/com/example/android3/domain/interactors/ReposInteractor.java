package com.example.android3.domain.interactors;

import com.example.android3.domain.repositories.ReposRepo;

import com.example.android3.data.models.Repo;

import java.util.List;

import io.reactivex.Single;

public class ReposInteractor {
    private ReposRepo reposRepo;

    public ReposInteractor(ReposRepo reposRepo) {
        this.reposRepo = reposRepo;
    }

    public Single<List<Repo>> getRepos(String name){
        return reposRepo.getRepos(name);
    }
}
