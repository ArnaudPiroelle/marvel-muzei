package com.arnaudpiroelle.marvelmuzei.core.utils;

import com.arnaudpiroelle.marvelmuzei.MarvelMuzeiApplication;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class TrackerUtils {
    public static void sendEvent(String category, String action, String label) {
        Tracker tracker = ((MarvelMuzeiApplication) Injector.getContext()).getTracker();
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());

    }

    public static void sendScreen(String screenName) {
        Tracker tracker = ((MarvelMuzeiApplication) Injector.getContext()).getTracker();
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.AppViewBuilder()
                .build());

    }
}
