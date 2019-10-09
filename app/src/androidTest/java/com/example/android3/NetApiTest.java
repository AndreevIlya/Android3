package com.example.android3;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android3.data.Endpoints;
import com.example.android3.data.models.GithubUser;
import com.example.android3.data.models.RepsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NetApiTest {
    private static MockWebServer mockWebServer;
    private static Endpoints endPointsMocked;

    @BeforeClass
    public static void setupServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
        mockWebServer.setDispatcher(new RequestDispatcher());

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        endPointsMocked =  new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(Endpoints.class);
    }

    @Test
    public void testSuccess(){
        endPointsMocked.getRepos().subscribe(new SingleObserver<List<RepsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<RepsModel> repsModels) {
                Log.i("TEST","Success");
                RepsModel rmSuccess = repsModels.get(0);
                String nameSuccess = rmSuccess.fullName;
                GithubUser ghu = rmSuccess.owner;
                String avatarSuccess = ghu.getAvatar();
                assertEquals(nameSuccess,"name");
                assertEquals(avatarSuccess,"url");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TEST","Error",e);
            }
        });
    }

    @AfterClass
    public static void shutdownServer() throws IOException {
        mockWebServer.shutdown();
    }

    private static class RequestDispatcher extends Dispatcher {
        @NotNull
        @Override
        public MockResponse dispatch(RecordedRequest request) {

            if(request.getPath().equals("/repositories")){
                return new MockResponse().setResponseCode(200)
                        .setBody("{full_name:name,owner:{avatar_url:url}}");
            }

            return new MockResponse().setResponseCode(404);
        }
    }

}
