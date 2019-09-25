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
import com.example.android3.data.models.Repo;
import com.example.android3.data.models.User;
import com.example.android3.data.network.Api;
import com.example.android3.data.network.RetrofitInit;
import com.example.android3.data.repositories.ReposRepoImpl;
import com.example.android3.data.repositories.UserRepoImpl;
import com.example.android3.data.repositories.UsersRepoImpl;
import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;
import com.example.android3.domain.repositories.ReposRepo;
import com.example.android3.domain.repositories.UserRepo;
import com.example.android3.domain.repositories.UsersRepo;
import com.example.android3.presentation.adapters.ReposAdapter;
import com.example.android3.presentation.adapters.UsersAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView resultUser;
    private RecyclerView recyclerView;

    private MainViewModel viewModel;
    private UsersAdapter usersAdapter;
    private ReposAdapter reposAdapter;

    private Observer<User> userObserver;
    private Observer<List<User>> usersObserver;
    private Observer<List<Repo>> reposObserver;

    private static final String name = "AndreevIlya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initViewModel();
        initRecyclesView();
        initObservers();
    }

    private void initObservers() {
        userObserver = user -> {
            resultUser.setText(getString(R.string.result_user,user.getName(),user.getId(),user.getUrl()));
            resultUser.setVisibility(View.VISIBLE);
        };
        usersObserver = users -> usersAdapter.setUsers(users);
        reposObserver = repos -> reposAdapter.setRepos(repos);
    }

    private void initRecyclesView() {
        recyclerView = findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViewModel() {
        Api api = RetrofitInit.newApiInstance();
        UserRepo userRepo = new UserRepoImpl(api);
        UsersRepo usersRepo = new UsersRepoImpl(api);
        ReposRepo reposRepo = new ReposRepoImpl(api);

        UserInteractor ui = new UserInteractor(userRepo);
        UsersInteractor usi = new UsersInteractor(usersRepo);
        ReposInteractor ri = new ReposInteractor(reposRepo);

        viewModel = new MainViewModel(ui,usi,ri,name);
        getLifecycle().addObserver(viewModel);
    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        resultUser = findViewById(R.id.result_user);
        Button getUserBtn = findViewById(R.id.getUser);
        Button getUsersBtn = findViewById(R.id.getUsers);
        Button getReposBtn = findViewById(R.id.getRepos);
        getUserBtn.setOnClickListener((v) -> onClickUser());
        getUsersBtn.setOnClickListener((v) -> onClickUsers());
        getReposBtn.setOnClickListener((v) -> onClickRepos());
    }

    private void onClickUser() {
        disposeObservers();
        viewModel.userLiveData.observe(this,userObserver);
    }

    private void onClickUsers(){
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        disposeObservers();
        viewModel.usersLiveData.observe(this,usersObserver);
    }

    private void onClickRepos(){
        reposAdapter = new ReposAdapter();
        recyclerView.setAdapter(reposAdapter);
        disposeObservers();
        viewModel.reposLiveData.observe(this,reposObserver);
    }

    private void disposeObservers() {
        if(viewModel.reposLiveData.hasObservers()){
            viewModel.reposLiveData.removeObserver(reposObserver);
        }
        if(viewModel.usersLiveData.hasObservers()){
            viewModel.usersLiveData.removeObserver(usersObserver);
        }
        if(viewModel.userLiveData.hasObservers()){
            viewModel.userLiveData.removeObserver(userObserver);
            resultUser.setText("");
            resultUser.setVisibility(View.INVISIBLE);
        }
    }
}

