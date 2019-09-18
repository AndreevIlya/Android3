package com.example.android3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    private EventBus<Long> bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewObserver tv1 = findViewById(R.id.field1);
        TextViewObserver tv2 = findViewById(R.id.field2);

        bus = new EventBus<>(tv1,tv2);
        Observable<Long> obs1 = makeObservable();
        Observable<Long> obs2 = makeObservable();
        Observable<Long> obs = Observable.merge(obs1,obs2);
        obs.subscribe(bus);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.dispose();
    }

    private Observable<Long> makeObservable(){
        return Observable.interval(100, TimeUnit.MILLISECONDS);
    }
}
