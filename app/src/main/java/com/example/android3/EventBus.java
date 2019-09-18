package com.example.android3;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventBus<L> extends DisposableObserver<L> {

    private Observer<L> obs1;
    private Observer<L> obs2;

    EventBus(Observer<L> obs1,Observer<L> obs2){
        this.obs1 = obs1;
        this.obs2 = obs2;
    }

    @Override
    public void onNext(L l) {
        Observable<L> ob = Observable.just(l);
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obs1);
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obs2);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Bus","error");
    }

    @Override
    public void onComplete() {
        Log.i("Bus","complete");
    }
}
