package com.arnaudpiroelle.marvelmuzei.source.command;

import com.google.android.apps.muzei.api.Artwork;

public interface Command {
    void execute(Artwork artwork);
}
