package com.arnaudpiroelle.muzei.marvel.source.command;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.async.DownloadAsyncCallback;
import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.UserCommand;

import java.io.File;

import static com.arnaudpiroelle.muzei.marvel.core.inject.Injector.getContext;


public class PublishCommand extends UserCommand implements Command, DownloadAsyncCallback {

    Artwork artwork;

    public PublishCommand() {
        super(2, getContext().getString(R.string.command_publish));
    }

    @Override
    public void execute(final Artwork artwork) {
        this.artwork = artwork;
        new SaveCommand(this).execute(artwork);
    }

    @Override
    public void doCallback(File file) {
        Context context = Injector.getContext();

        String artist = context.getResources().getString(R.string.marvel_copyright).trim();

        String appUrl = context.getString(R.string.app_url);
        String notifText = String.format(context.getString(R.string.share_template), artwork.getTitle().trim(), artist, appUrl);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

        shareIntent.putExtra(Intent.EXTRA_TEXT, notifText);
        context.startActivity(shareIntent);
    }
}
