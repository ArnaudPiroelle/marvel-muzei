package com.arnaudpiroelle.muzei.marvel.core.utils;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;

public class AppCheckerUtils {

    public AppCheckerUtils() {
    }

    public boolean checkApplicationInstalled(String packageName) {
        PackageManager pm = Injector.getContext().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
