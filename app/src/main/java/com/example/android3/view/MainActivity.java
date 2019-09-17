package com.example.android3.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android3.R;
import com.example.android3.presenter.Presenter;

public class MainActivity extends MvpAppCompatActivity implements MoxyView {

    @InjectPresenter
    Presenter presenter;

    private EditText input;
    private TextView output;
    private TextView smallOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        smallOutput = findViewById(R.id.output_small);

        initListener();
    }

    private void initListener() {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("TextWatcher", "before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("TextWatcher", "on " + s);
                presenter.processTextInput(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("TextWatcher", "after");
            }
        });
    }

    @Override
    public void clearOutput() {
        output.setText("");
    }

    @Override
    public void appendSmallText(String text) {
        smallOutput.append(text);
    }

    @Override
    public void clearSmallOutput() {
        smallOutput.setText("");
    }

    @Override
    public void clearInput() {
        input.setText("");
    }

    @Override
    public void appendText(String text) {
        output.append(text);
    }
}
