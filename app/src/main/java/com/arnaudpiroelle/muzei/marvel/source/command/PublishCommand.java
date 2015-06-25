package com.arnaudpiroelle.muzei.marvel.source.command;

import android.content.Context;
import android.content.Intent;

import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.UserCommand;

import static com.arnaudpiroelle.muzei.marvel.core.inject.Injector.getContext;

public class PublishCommand extends UserCommand implements Command {

    public PublishCommand() {
        super(2, getContext().getString(R.string.command_publish));
    }
    
    @Override
    public void execute(Artwork artwork) {
        Context context = Injector.getContext();

        String detailUrl = artwork.getImageUri().toString();
        String artist = context.getResources().getString(R.string.marvel_copyright).trim();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        String appUrl = context.getString(R.string.app_url);
        String notifText = String.format(context.getString(R.string.share_template), artwork.getTitle().trim(), artist, detailUrl, appUrl);

        shareIntent.putExtra(Intent.EXTRA_TEXT, notifText);
        shareIntent = Intent.createChooser(shareIntent, context.getString(R.string.share_with));
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, getContext().getString(R.string.chooser_market)));
    }
}
