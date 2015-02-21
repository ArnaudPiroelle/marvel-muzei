package com.arnaudpiroelle.marvelmuzei;

import android.app.Application;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.core.inject.module.RootModule;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
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

    synchronized public Tracker getTracker() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        return analytics.newTracker(R.xml.app_tracker);
    }

}
