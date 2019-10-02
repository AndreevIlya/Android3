package com.example.android3.domain.repositories;

import com.example.android3.data.entities.Repo;

import java.util.List;

import io.reactivex.Single;

public interface Repos {
    Single<List<Repo>> getRepos(String name);
}
