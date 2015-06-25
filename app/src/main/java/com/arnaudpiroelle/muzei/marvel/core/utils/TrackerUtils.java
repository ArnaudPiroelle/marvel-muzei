package com.arnaudpiroelle.muzei.marvel.core.utils;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

public class TrackerUtils {

    private Tracker tracker;

    @Inject
    public TrackerUtils(Tracker tracker) {
        this.tracker = tracker;
    }



    public void sendEvent(String category, String action, String label) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());

    }

    public void sendScreen(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.AppViewBuilder()
                .build());

    }
}
