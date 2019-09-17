package com.example.android3.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.android3.model.Model;
import com.example.android3.view.MoxyView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class Presenter extends MvpPresenter<MoxyView> {

    private Model model;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new Model();
    }

    @Override
    public void attachView(MoxyView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(MoxyView view) {
        super.detachView(view);
    }

    public void processTextInput(CharSequence text){
        if(text.length() != 0 && text.charAt(text.length() - 1) == ' '){
            model.addToContent(text.toString());
            getViewState().clearInput();
            List<String> data = model.getAll();
            getViewState().clearOutput();
            sendData(data);
        }
    }

    private void sendData(List<String> data){
        Observable.fromIterable(data)
                .subscribe(new DisposableObserver<String>() {

                    @Override
                    public void onNext(String s) {
                        getViewState().appendText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Observer","error ;(");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Observer","complete");
                    }
                });
    }
}
