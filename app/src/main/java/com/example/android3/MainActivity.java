package com.example.android3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android3.presenter.Presenter;

public class MainActivity extends AppCompatActivity {

    @InjectPresenter
    Presenter presenter;

    private EditText input;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        initListeners();
    }

    private void initListeners() {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("TextWatcher","before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("TextWatcher","on");
                presenter.processTextInput(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("TextWatcher","after");
            }
        });
    }
}
