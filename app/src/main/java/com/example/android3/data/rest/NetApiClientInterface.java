package com.example.android3.data.rest;

import com.example.android3.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;

public interface NetApiClientInterface {
    Single<List<RepsModel>> getReps();

}
