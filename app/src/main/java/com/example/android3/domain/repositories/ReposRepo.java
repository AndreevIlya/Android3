package com.example.android3.domain.repositories;

import com.example.android3.data.models.Repo;

import java.util.List;

import io.reactivex.Single;

public interface ReposRepo {
    Single<List<Repo>> getRepos(String name);
}
