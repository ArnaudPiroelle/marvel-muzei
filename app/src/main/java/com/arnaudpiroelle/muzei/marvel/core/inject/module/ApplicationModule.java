package com.arnaudpiroelle.muzei.marvel.core.inject.module;

import android.content.Context;

import com.arnaudpiroelle.muzei.marvel.MarvelMuzeiApplication;
import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.api.service.MarvelCacheApiService;
import com.arnaudpiroelle.muzei.marvel.core.source.SourceRegistry;
import com.arnaudpiroelle.muzei.marvel.core.utils.AppCheckerUtils;
import com.arnaudpiroelle.muzei.marvel.core.utils.MarvelCacheAPIUtils;
import com.arnaudpiroelle.muzei.marvel.source.MarvelMuzeiArtSource;
import com.arnaudpiroelle.muzei.marvel.ui.launcher.LauncherActivity;
import com.arnaudpiroelle.muzei.marvel.ui.settings.SettingsActivity;
import com.arnaudpiroelle.muzei.marvel.ui.settings.fragment.MyTagsFragment;
import com.arnaudpiroelle.muzei.marvel.ui.settings.fragment.PrefsFragment;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        injects = {
                MarvelMuzeiApplication.class,
                LauncherActivity.class,
                SettingsActivity.class,
                MarvelMuzeiArtSource.class,
                MyTagsFragment.class,
                PrefsFragment.class,
                SourceRegistry.class,
                AppCheckerUtils.class
        })
public class ApplicationModule {

    @Provides
    @Singleton
    Tracker providesTracker(Context context){
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.setLocalDispatchPeriod(1800);

        Tracker tracker = analytics.newTracker(R.xml.app_tracker);
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        return tracker;
    }

    @Provides
    @Singleton
    MarvelCacheApiService provideMarvelCacheApiService() {
        return MarvelCacheAPIUtils.getService();
    }

    @Provides
    @Singleton
    AppCheckerUtils provideAppCheckerUtils() {
        return new AppCheckerUtils();
    }

}
