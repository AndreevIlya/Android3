package com.example.android3.data.database;

import java.util.List;

public interface DBProvider<T, R> {
    void insert(T data);

    void update(T data);

    void delete(T data);

    List<R> selectAll();

    R selectByName(String name);
}
