package com.example.android3.view;

import com.arellomobile.mvp.MvpView;

public interface MoxyView extends MvpView {
    void appendText(String text);
    void clearOutput();
    void appendSmallText(String text);
    void clearSmallOutput();

    void clearInput();
}
