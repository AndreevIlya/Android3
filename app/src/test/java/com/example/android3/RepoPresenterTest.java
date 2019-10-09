package com.example.android3;


import com.example.android3.data.models.RepsModel;
import com.example.android3.data.rest.NetApiClientInterface;
import com.example.android3.presenter.RepsPresenter;
import com.example.android3.presenter.RepsView;
import com.example.android3.presenter.RepsView$$State;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RepoPresenterTest {

    private RepsPresenter presenter;

    @Mock
    private RepsView$$State repsViewState;

    @Mock
    private RepsView view;

    @Mock
    private NetApiClientInterface client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new RepsPresenter(client);
    }

    @Test
    public void testShowList() {
        RepsModel model = new RepsModel();
        List<RepsModel> list = new ArrayList<>();
        list.add(model);
        when(client.getReps()).thenReturn(Single.just(list));
        presenter.attachView(view);
        presenter.setViewState(repsViewState);
        verify(view).showLoading();
        verify(view).showRepoList(list);
        verify(view).hideLoading();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testEmptyList(){
        List<RepsModel> list = new ArrayList<>();
        when(client.getReps()).thenReturn(Single.just(list));
        presenter.attachView(view);
        presenter.setViewState(repsViewState);
        verify(view).showLoading();
        verify(view).showEmptyState();
        verify(view).hideLoading();
        verifyNoMoreInteractions(view);
    }
}
