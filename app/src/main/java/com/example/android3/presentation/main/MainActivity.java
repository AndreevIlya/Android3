package com.example.android3.presentation.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3.R;
import com.example.android3.data.database.DBProvider;
import com.example.android3.data.database.RepoSugarImpl;
import com.example.android3.data.database.UserSugarImpl;
import com.example.android3.data.entities.Repo;
import com.example.android3.data.entities.User;
import com.example.android3.data.network.RetrofitInit;
import com.example.android3.data.network.RetrofitProvider;
import com.example.android3.data.repositories.ReposImpl;
import com.example.android3.data.repositories.UserImpl;
import com.example.android3.data.repositories.UsersImpl;
import com.example.android3.domain.interactors.ReposInteractor;
import com.example.android3.domain.interactors.UserInteractor;
import com.example.android3.domain.interactors.UsersInteractor;
import com.example.android3.domain.repositories.Repos;
import com.example.android3.domain.repositories.UserRepo;
import com.example.android3.domain.repositories.Users;
import com.example.android3.presentation.Factories.MainViewModelFactory;
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
        //SugarContext.init(getApplicationContext());
        initViews();
        initViewModel();
        initRecyclesView();
        initObservers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restorePresentation();
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }*/

    private void restorePresentation() {
        String active = viewModel.getActivePresentation();
        if (active != null) {
            switch (active) {
                case "user":
                    onClickUser();
                    break;
                case "users":
                    onClickUsers();
                    break;
                case "repos":
                    onClickRepos();
                    break;
            }
        }
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
        RetrofitProvider retrofitProvider = RetrofitInit.newApiInstance();
        DBProvider<User,User> userSugarDB = new UserSugarImpl();
        DBProvider<Repo,Repo> repoSugarDB = new RepoSugarImpl();
        UserRepo user = new UserImpl(retrofitProvider, userSugarDB);
        Users users = new UsersImpl(retrofitProvider, userSugarDB);
        Repos repos = new ReposImpl(retrofitProvider, repoSugarDB);

        UserInteractor ui = new UserInteractor(user);
        UsersInteractor usi = new UsersInteractor(users);
        ReposInteractor ri = new ReposInteractor(repos);

        viewModel = ViewModelProviders.of(this, new MainViewModelFactory(ui,usi,ri,name)).get(MainViewModel.class);
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
        hideUserInfo();
        viewModel.userLiveData.observe(this,userObserver);
        viewModel.setActivePresentation("user");
    }

    private void onClickUsers(){
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        hideUserInfo();
        viewModel.usersLiveData.observe(this,usersObserver);
        viewModel.setActivePresentation("users");
    }

    private void onClickRepos(){
        reposAdapter = new ReposAdapter();
        recyclerView.setAdapter(reposAdapter);
        hideUserInfo();
        viewModel.reposLiveData.observe(this,reposObserver);
        viewModel.setActivePresentation("repos");
    }

    private void hideUserInfo() {
        /*if(viewModel.reposLiveData.hasObservers()){
            viewModel.reposLiveData.removeObserver(reposObserver);
        }
        if(viewModel.usersLiveData.hasObservers()){
            viewModel.usersLiveData.removeObserver(usersObserver);
        }*/
        if(viewModel.userLiveData.hasObservers()){
            //viewModel.userLiveData.removeObserver(userObserver);
            resultUser.setText("");
            resultUser.setVisibility(View.INVISIBLE);
        }
    }
}

