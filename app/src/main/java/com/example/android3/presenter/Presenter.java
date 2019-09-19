package com.example.android3.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.android3.model.Model;
import com.example.android3.view.MoxyView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@InjectViewState
public class Presenter extends MvpPresenter<MoxyView> {

    private static final int SMALL_LENGTH = 5;
    private Model model;
    private Disposable rxToModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new Model();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rxToModel.dispose();
    }

    public void processTextInput(CharSequence text){
        if(text.length() != 0 && text.charAt(text.length() - 1) == ' '){
            model.addToContent(text.toString());
            clearFields();
            List<String> data = model.getAll();
            sendData(data,false);
            data = model.getFromIndex(SMALL_LENGTH);
            sendData(data,true);
        }
    }

    private void clearFields() {
        getViewState().clearInput();
        getViewState().clearOutput();
        getViewState().clearSmallOutput();
    }

    private void sendData(List<String> data, final boolean toSmall){
        rxToModel = Observable.fromIterable(data)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        if(toSmall) getViewState().appendSmallText(s);
                        else getViewState().appendText(s);
                    }
                });
    }
}
