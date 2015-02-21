package com.arnaudpiroelle.marvelmuzei.core.async;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import com.arnaudpiroelle.marvelmuzei.R;

import javax.inject.Inject;
import java.io.File;

import static android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE;
import static android.net.Uri.fromFile;

public class DownloadAsyncCallback {

    private static int NOTIFICATION_ID = 12345789;

    private Context context;
    private NotificationManager notificationManager;

    @Inject
    public DownloadAsyncCallback(NotificationManager notificationManager, Context context) {
        this.notificationManager = notificationManager;

        this.context = context;
    }

    void doCallback(File file){
        if (file == null)
            return;

        Bitmap largeIcon = BitmapFactory.decodeFile(file.getAbsolutePath());

        Intent imageIntent = new Intent(Intent.ACTION_VIEW);
        imageIntent.setDataAndType(fromFile(file),"image/*");

        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(context, 0, imageIntent, 0);

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setAutoCancel(true)
                .setContentTitle(file.getName())
                .setContentText(context.getString(R.string.notification_new_wallpaper))
                .setLargeIcon(largeIcon)
                .setContentIntent(mapPendingIntent);
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle(nb)
                .bigLargeIcon(null)
                .setBigContentTitle(file.getName())
                .setSummaryText(context.getString(R.string.notification_new_wallpaper))
                .bigPicture(largeIcon);

        notificationManager.notify(NOTIFICATION_ID,style.build());

        context.sendBroadcast(new Intent(ACTION_MEDIA_SCANNER_SCAN_FILE, fromFile(file)));
    }
}