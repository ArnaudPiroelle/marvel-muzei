package com.arnaudpiroelle.muzei.marvel.source.command;

import android.os.Environment;

import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.async.DownloadAsyncCallback;
import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;
import com.arnaudpiroelle.muzei.marvel.core.utils.HttpUtils;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.UserCommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SaveCommand extends UserCommand implements Command {

    private DownloadAsyncCallback callback;

    public SaveCommand(DownloadAsyncCallback callback) {
        super(1, Injector.getContext().getString(R.string.command_save));
        this.callback = callback;
    }

    @Override
    public void execute(final Artwork artwork) {
        Observable.create(new Observable.OnSubscribe<File>() {
                              @Override
                              public void call(Subscriber<? super File> subscriber) {
                                  try {
                                      URL url = new URL(artwork.getImageUri().toString());
                                      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                      connection.connect();

                                      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                                          return;
                                      }

                                      InputStream input = connection.getInputStream();

                                      File wallpaper = getWallpaperFile(artwork.getTitle());

                                      HttpUtils.writeFile(input, wallpaper);

                                      subscriber.onNext(wallpaper);

                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  } finally {
                                      subscriber.onCompleted();
                                  }
                              }
                          }
        )
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<File>() {
                            @Override
                            public void call(File file) {
                                callback.doCallback(file);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
    }

    private File getWallpaperFile(String title) throws IOException {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MarvelMuzei");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File wallpaper = new File(folder, title + ".jpg");
        if (wallpaper.exists()) {
            wallpaper.delete();
        }

        wallpaper.createNewFile();

        return wallpaper;
    }


}
