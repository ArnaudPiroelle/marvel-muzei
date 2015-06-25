package com.arnaudpiroelle.marvelmuzei.core.inject.module;

import android.content.Context;

import com.arnaudpiroelle.marvelmuzei.MarvelMuzeiApplication;
import com.arnaudpiroelle.marvelmuzei.R;
import com.arnaudpiroelle.marvelmuzei.core.api.service.MarvelCacheApiService;
import com.arnaudpiroelle.marvelmuzei.core.source.SourceRegistry;
import com.arnaudpiroelle.marvelmuzei.core.utils.AppCheckerUtils;
import com.arnaudpiroelle.marvelmuzei.core.utils.MarvelCacheAPIUtils;
import com.arnaudpiroelle.marvelmuzei.source.MarvelMuzeiArtSource;
import com.arnaudpiroelle.marvelmuzei.ui.launcher.LauncherActivity;
import com.arnaudpiroelle.marvelmuzei.ui.settings.SettingsActivity;
import com.arnaudpiroelle.marvelmuzei.ui.settings.fragment.MyTagsFragment;
import com.arnaudpiroelle.marvelmuzei.ui.settings.fragment.PrefsFragment;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

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
