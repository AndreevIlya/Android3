package com.example.android3;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android3.data.models.RepsModel;
import com.example.android3.data.rest.NetApiClient;
import com.example.android3.presenter.RepsPresenter;
import com.example.android3.presenter.RepsView;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements RepsView {

    private ProgressBar progress;
    private View content;
    private View empty;

    @InjectPresenter
    RepsPresenter repsPresenter;

    @ProvidePresenter
    RepsPresenter providePresenter() {
        return new RepsPresenter(NetApiClient.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.contentView);
        progress = findViewById(R.id.loadingView);
        empty = findViewById(R.id.emptyView);
    }

    @Override
    public void showLoading() {
        empty.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showRepoList(List<RepsModel> list) {
        empty.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        empty.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}

