package com.example.android3.data.repositories;

import com.example.android3.data.models.Repo;
import com.example.android3.data.network.Api;
import com.example.android3.domain.repositories.ReposRepo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ReposRepoImpl implements ReposRepo {
    private Api api;

    public ReposRepoImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<List<Repo>> getRepos(String name) {
        return api.loadRepos(name)
                .subscribeOn(Schedulers.io());
    }
}
