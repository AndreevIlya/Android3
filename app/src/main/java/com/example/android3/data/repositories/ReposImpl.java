package com.example.android3.data.repositories;

import com.example.android3.data.database.DBProvider;
import com.example.android3.data.entities.Repo;
import com.example.android3.data.models.retrofit.RepoInRetrofit;
import com.example.android3.data.network.RetrofitProvider;
import com.example.android3.domain.repositories.Repos;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ReposImpl implements Repos {
    private RetrofitProvider retrofitProvider;
    private DBProvider<Repo,Repo> repoSugarDB;

    public ReposImpl(RetrofitProvider retrofitProvider,
                     DBProvider<Repo, Repo> repoSugarDB) {
        this.retrofitProvider = retrofitProvider;
        this.repoSugarDB = repoSugarDB;
    }

    @Override
    public Single<List<Repo>> getRepos(String name) {
        return retrofitProvider.loadRepos(name)
                .subscribeOn(Schedulers.io())
                .map(rirs -> {
                    List<Repo> repos = new ArrayList<>();
                    for(RepoInRetrofit rir : rirs){
                        repos.add(rir.convertToEntity());
                    }
                    return repos;
                })
                .doOnSuccess(repos -> {
                    for(Repo repo : repos){
                        repoSugarDB.insert(repo);
                    }
                });
    }
}
