package com.example.android3.data.database;

import com.example.android3.data.entities.Repo;
import com.example.android3.data.models.sugar.RepoInSugar;

import java.util.ArrayList;
import java.util.List;

public class RepoSugarImpl implements DBProvider<Repo, Repo> {
    @Override
    public void insert(Repo data) {
        List<RepoInSugar> lRis = RepoInSugar.find(RepoInSugar.class, "name = ?", data.getName());
        if (lRis.size() != 1) {//Insert is allowed only once
            RepoInSugar ris = new RepoInSugar(data);
            ris.save();
        }
    }

    @Override
    public void update(Repo data) {
        List<RepoInSugar> lRis = RepoInSugar.find(RepoInSugar.class, "name = ?", data.getName());
        if (lRis.size() == 0) {
            RepoInSugar ris = new RepoInSugar(data);
            ris.save();
        } else if (lRis.size() == 1){
            RepoInSugar ris = new RepoInSugar(data);
            ris.save();
        }else {
            throw new RuntimeException("Multiple items in the repo sugar db with matching names: " + data.getName() + ".");
        }
    }

    @Override
    public void delete(Repo data) {
        List<RepoInSugar> lRis = RepoInSugar.find(RepoInSugar.class, "name = ?", data.getName());
        for (RepoInSugar ris : lRis) ris.delete();
    }

    @Override
    public List<Repo> selectAll() {
        List<RepoInSugar> lRis = RepoInSugar.listAll(RepoInSugar.class);
        List<Repo> repos = new ArrayList<>();
        for (RepoInSugar ris : lRis) {
            repos.add(ris.convertToEntity());
        }
        return repos;
    }

    @Override
    public Repo selectByName(String name) {
        List<RepoInSugar> lRis = RepoInSugar.find(RepoInSugar.class, "name = ?", name);
        if (lRis.size() == 1) return lRis.get(0).convertToEntity();
        throw new RuntimeException("Error while selecting from repo sugar db by name.");
    }
}
