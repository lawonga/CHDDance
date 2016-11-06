package com.dance.chd.chddance.view;

import android.app.Application;

import com.dance.chd.chddance.helper.DancerHelper;

/**
 * Created by Andy on 11/5/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DancerHelper.getInstance().initInstance();
    }
}
