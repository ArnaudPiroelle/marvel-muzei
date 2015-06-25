package com.arnaudpiroelle.muzei.marvel;

import android.app.Application;

import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;
import com.arnaudpiroelle.muzei.marvel.core.inject.module.RootModule;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import static com.arnaudpiroelle.muzei.marvel.BuildConfig.DEBUG;

public class MarvelMuzeiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.setContext(getApplicationContext());
        Injector.init(new RootModule(), this);

        if(!DEBUG){
            Fabric.with(this, new Crashlytics());
        }
    }

}
