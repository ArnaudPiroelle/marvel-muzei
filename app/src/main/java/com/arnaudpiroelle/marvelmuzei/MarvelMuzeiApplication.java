package com.arnaudpiroelle.marvelmuzei;

import android.app.Application;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.core.inject.module.RootModule;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import static com.arnaudpiroelle.marvelmuzei.BuildConfig.DEBUG;

public class MarvelMuzeiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.setContext(getApplicationContext());
        Injector.init(new RootModule(), this);

        if (!DEBUG){
            Fabric.with(this, new Crashlytics());
        }
    }

}
