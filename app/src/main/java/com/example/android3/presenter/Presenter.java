package com.example.android3.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.android3.model.Model;
import com.example.android3.view.MoxyView;

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

    public void processTextInput(String text){
        model.addToContent(text);
    }
}
