package com.example.android3.presentation.main;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.android3.data.entities.Repo;
import com.example.android3.data.entities.User;
import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends ViewModel implements LifecycleObserver {

    private UserInteractor ui;
    private UsersInteractor usi;
    private ReposInteractor ri;
    private String name;

    private String activePresentation = "none";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    MutableLiveData<User> userLiveData = new MutableLiveData<>();
    MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    MutableLiveData<List<Repo>> reposLiveData = new MutableLiveData<>();
    MutableLiveData<String> activePresentationLiveData = new MutableLiveData<>();


    public MainViewModel(UserInteractor ui, UsersInteractor usi, ReposInteractor ri, String name) {
        this.ui = ui;
        this.usi = usi;
        this.ri = ri;
        this.name = name;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        compositeDisposable.add(ui.getUser(name).subscribe(
                (result) -> userLiveData.postValue(result),
                (e) -> Log.e("ViewModel","Submit on user failed.",e)
        ));
        compositeDisposable.add(usi.getUsers().subscribe(
                (result) -> usersLiveData.postValue(result),
                (e) -> Log.e("ViewModel","Submit on users failed.",e)
        ));
        compositeDisposable.add(ri.getRepos(name).subscribe(
                (result) -> reposLiveData.postValue(result),
                (e) -> Log.e("ViewModel","Submit on repos failed.",e)
        ));
        compositeDisposable.add(getActivePresentationObservable().subscribe(
                (result) -> activePresentationLiveData.postValue(activePresentation),
                (e) -> Log.e("ViewModel","Submit on active failed.",e)
        ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private Single<String> getActivePresentationObservable(){
        return Single.fromCallable(() -> activePresentation);
    }

    void saveActiveState(String state){
        activePresentationLiveData.setValue(state);
    }
}
