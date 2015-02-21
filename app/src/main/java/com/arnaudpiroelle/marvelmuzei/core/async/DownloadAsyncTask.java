package com.arnaudpiroelle.marvelmuzei.core.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import com.google.android.apps.muzei.api.Artwork;

import javax.inject.Inject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<Artwork, Void, File> {

    private DownloadAsyncCallback downloadAsyncCallback;

    @Inject
    public DownloadAsyncTask(DownloadAsyncCallback downloadAsyncCallback) {
        this.downloadAsyncCallback = downloadAsyncCallback;
    }

    @Override
    protected File doInBackground(Artwork... sUrl) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(sUrl[0].getImageUri().toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            input = connection.getInputStream();

            File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MarvelMuzei");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File nomedia = new File(folder.getAbsolutePath() + "/.nomedia");
            if (nomedia.exists()) {
                nomedia.delete();
            }

            File wallpaper = new File(folder, sUrl[0].getTitle() + ".jpg");
            if (wallpaper.exists()) {
                wallpaper.delete();
            }

            output = new FileOutputStream(wallpaper);

            Bitmap b = BitmapFactory.decodeStream(input);
            b.compress(Bitmap.CompressFormat.JPEG, 100, output);

            return wallpaper;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }

    }

    @Override
    protected void onPostExecute(File file) {
        if (downloadAsyncCallback != null) {
            downloadAsyncCallback.doCallback(file);
        }
    }

}
