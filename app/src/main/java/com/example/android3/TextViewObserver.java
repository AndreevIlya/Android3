package com.example.android3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TextViewObserver extends AppCompatTextView implements Observer<Long> {
    public TextViewObserver(Context context) {
        super(context);
    }

    public TextViewObserver(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewObserver(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Long l) {
        this.setText(String.format(Locale.ENGLISH,"%d",l));
    }

    @Override
    public void onError(Throwable e) {
        Log.e("View","error");
    }

    @Override
    public void onComplete() {
        Log.i("View","complete");
    }
}
