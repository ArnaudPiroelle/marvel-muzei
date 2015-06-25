package com.arnaudpiroelle.muzei.marvel.source.command;

import com.google.android.apps.muzei.api.Artwork;

public interface Command {
    void execute(Artwork artwork);
}
