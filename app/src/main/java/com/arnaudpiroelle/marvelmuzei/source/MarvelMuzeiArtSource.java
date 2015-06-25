package com.arnaudpiroelle.marvelmuzei.source;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Html;
import android.util.Log;

import com.arnaudpiroelle.marvelmuzei.core.api.response.Data;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.core.source.CustomSource;
import com.arnaudpiroelle.marvelmuzei.core.source.SourceRegistry;
import com.arnaudpiroelle.marvelmuzei.core.utils.PreferencesUtils;
import com.arnaudpiroelle.marvelmuzei.core.utils.TrackerUtils;
import com.arnaudpiroelle.marvelmuzei.source.command.PublishCommand;
import com.arnaudpiroelle.marvelmuzei.source.command.SaveCommand;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource;
import com.google.android.apps.muzei.api.UserCommand;

import javax.inject.Inject;

import static android.net.ConnectivityManager.TYPE_WIFI;
import static com.arnaudpiroelle.marvelmuzei.core.utils.Constants.ACTION_RESCHEDULE;
import static com.arnaudpiroelle.marvelmuzei.core.utils.Constants.APP_TAG;
import static com.arnaudpiroelle.marvelmuzei.core.utils.Constants.SOURCE_NAME;

public class MarvelMuzeiArtSource extends RemoteMuzeiArtSource {

    @Inject
    protected Context context;

    @Inject
    protected PreferencesUtils preferencesUtils;

    @Inject
    protected ConnectivityManager connectivityManager;

    @Inject
    protected SourceRegistry sourceRegistry;

    @Inject
    TrackerUtils trackerUtils;

    private boolean wifiOnly;
    private String activeSource;
    private String interval;

    SaveCommand saveCommand;
    PublishCommand publishCommand;

    public MarvelMuzeiArtSource() {
        super(SOURCE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.inject(this);

        saveCommand = new SaveCommand();
        publishCommand = new PublishCommand();

        setUserCommands(
                new UserCommand(BUILTIN_COMMAND_ID_NEXT_ARTWORK),
                saveCommand,
                publishCommand);

        initPrefs();
    }

    private void initPrefs() {
        wifiOnly = preferencesUtils.isWifiEnable();
        activeSource = preferencesUtils.getActiveSource();
        interval = preferencesUtils.getRefreshInterval();
    }

    @Override
    protected void onTryUpdate(int i) throws RetryException {
        update(activeSource);
        scheduleUpdateRefresh();
    }

    private void scheduleUpdateRefresh() {
        scheduleUpdate(System.currentTimeMillis() + Long.valueOf(interval) * 60 * 1000);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ACTION_RESCHEDULE.equals(intent.getAction())) {
            initPrefs();
            try {
                update(activeSource);
            } catch (RetryException e) {
                Log.e(APP_TAG, "Erreur on service update", e);
            }
        } else {
            super.onHandleIntent(intent);
        }

    }

    @Override
    protected void onCustomCommand(int id) {
        if (saveCommand.getId() == id) {
            saveCommand.execute(getCurrentArtwork());
        } else if (publishCommand.getId() == id) {
            publishCommand.execute(getCurrentArtwork());
        } else {
            super.onCustomCommand(id);
        }
    }

    public void update(String sourceId) throws RetryException {
        Log.i(APP_TAG, "Update");

        if (!wifiOnly || isConnectedWifi()) {
            CustomSource customSource = sourceRegistry.getSource(sourceId);

            if (customSource != null) {
                Data data = customSource.getData(preferencesUtils.getDataTypes(), preferencesUtils.getQualities());

                if (data != null) {
                    trackerUtils.sendEvent("MarvelMuzeiSource", "update", data.getName());

                    Artwork.Builder artworkBuilder = new Artwork.Builder()
                            .title(data.getName())
                            .token(String.valueOf(data.getId()))
                            .imageUri(Uri.parse(data.getImage()));

                    if (data.getDescription() != null && !data.getDescription().isEmpty()) {
                        artworkBuilder.byline(Html.fromHtml(data.getDescription()).toString());
                    }

                    if (data.getUrl() != null) {
                        artworkBuilder.viewIntent(new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl())));
                    }

                    publishArtwork(artworkBuilder.build());
                }
            }
        }
    }

    private boolean isConnectedWifi() {
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null
                && info.isConnected()
                && info.getType() == TYPE_WIFI);
    }
}
