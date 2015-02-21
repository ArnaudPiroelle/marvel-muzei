package com.arnaudpiroelle.marvelmuzei.source.command;

import com.google.android.apps.muzei.api.Artwork;

public interface Command {
    public void execute(Artwork artwork);
}
