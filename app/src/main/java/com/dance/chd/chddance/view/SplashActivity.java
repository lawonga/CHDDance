package com.dance.chd.chddance.view;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.Callable;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Andy on 11/6/2016.
 */

public class SplashActivity extends BaseActivity {
    SplashActivity splashActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Single.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(1500);
                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(splashActivity, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
