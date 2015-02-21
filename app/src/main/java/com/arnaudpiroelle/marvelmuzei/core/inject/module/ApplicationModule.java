package com.arnaudpiroelle.marvelmuzei.core.inject.module;

import com.arnaudpiroelle.marvelmuzei.MarvelMuzeiApplication;
import com.arnaudpiroelle.marvelmuzei.core.api.service.MarvelCacheApiService;
import com.arnaudpiroelle.marvelmuzei.core.source.SourceRegistry;
import com.arnaudpiroelle.marvelmuzei.core.utils.AppCheckerUtils;
import com.arnaudpiroelle.marvelmuzei.core.utils.MarvelCacheAPIUtils;
import com.arnaudpiroelle.marvelmuzei.source.MarvelMuzeiArtSource;
import com.arnaudpiroelle.marvelmuzei.ui.launcher.LauncherActivity;
import com.arnaudpiroelle.marvelmuzei.ui.settings.SettingsActivity;
import com.arnaudpiroelle.marvelmuzei.ui.settings.fragment.MyTagsFragment;
import com.arnaudpiroelle.marvelmuzei.ui.settings.fragment.PrefsFragment;
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
    MarvelCacheApiService provideMarvelCacheApiService() {
        return MarvelCacheAPIUtils.getService();
    }

    @Provides
    @Singleton
    AppCheckerUtils provideAppCheckerUtils() {
        return new AppCheckerUtils();
    }

}
