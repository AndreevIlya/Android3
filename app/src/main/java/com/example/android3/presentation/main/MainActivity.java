package com.example.android3.presentation.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3.R;
import com.example.android3.data.entities.Repo;
import com.example.android3.data.entities.User;
import com.example.android3.di.app.AppComponent;
import com.example.android3.di.main.AdaptersModule;
import com.example.android3.di.main.DaggerMainComponent;
import com.example.android3.di.main.InteractorsModule;
import com.example.android3.di.main.ViewModelModule;
import com.example.android3.presentation.adapters.ReposAdapter;
import com.example.android3.presentation.adapters.UsersAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView resultUser;
    private RecyclerView recyclerView;

    private Observer<User> userObserver;
    private Observer<List<User>> usersObserver;
    private Observer<List<Repo>> reposObserver;
    private Observer<String> activeStateObserver;

    private Map<String, ActionOnClick> actionsMap = initActionsMap();

    @Inject
    UsersAdapter usersAdapter;
    @Inject
    ReposAdapter reposAdapter;
    @Inject
    MainViewModel viewModel;

    public static final String NAME = "AndreevIlya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initDependencies();
        initRecyclesView();
        initObservers();

        getLifecycle().addObserver(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.activePresentationLiveData.observe(this, activeStateObserver);
    }

    private void initObservers() {
        userObserver = user -> {
            resultUser.setText(getString(R.string.result_user, user.getName(), user.getId(), user.getUrl()));
            resultUser.setVisibility(View.VISIBLE);
        };
        usersObserver = users -> usersAdapter.setUsers(users);
        reposObserver = repos -> reposAdapter.setRepos(repos);
        activeStateObserver = active -> Objects.requireNonNull(actionsMap.get(active)).doOnClick();
    }

    private void initRecyclesView() {
        recyclerView = findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDependencies() {
        AppComponent appComp = ((MainApp) getApplication()).getAppComponent();

        DaggerMainComponent
                .builder()
                .appComponent(appComp)
                .interactorsModule(new InteractorsModule())
                .adaptersModule(new AdaptersModule())
                .viewModelModule(new ViewModelModule(this))
                .build()
                .inject(this);
    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        resultUser = findViewById(R.id.result_user);
        Button getUserBtn = findViewById(R.id.getUser);
        Button getUsersBtn = findViewById(R.id.getUsers);
        Button getReposBtn = findViewById(R.id.getRepos);
        getUserBtn.setOnClickListener((v) -> viewModel.activePresentationLiveData.setValue("user"));
        getUsersBtn.setOnClickListener((v) -> viewModel.activePresentationLiveData.setValue("users"));
        getReposBtn.setOnClickListener((v) -> viewModel.activePresentationLiveData.setValue("repos"));
    }

    private void onClickUser() {
        viewModel.userLiveData.observe(this, userObserver);
        recyclerView.setAdapter(null);
        resultUser.setVisibility(View.VISIBLE);
    }

    private void onClickUsers() {
        recyclerView.setAdapter(usersAdapter);
        hideUserInfo();
        viewModel.usersLiveData.observe(this, usersObserver);
    }

    private void onClickRepos() {
        recyclerView.setAdapter(reposAdapter);
        hideUserInfo();
        viewModel.reposLiveData.observe(this, reposObserver);
    }

    private void hideUserInfo() {
        resultUser.setText("");
        resultUser.setVisibility(View.INVISIBLE);
    }

    private Map<String, ActionOnClick> initActionsMap() {
        Map<String, ActionOnClick> map = new HashMap<>();
        map.put("user", this::onClickUser);
        map.put("users", this::onClickUsers);
        map.put("repos", this::onClickRepos);
        map.put("none", () -> {
        });
        return map;
    }

    private interface ActionOnClick {
        void doOnClick();
    }
}

