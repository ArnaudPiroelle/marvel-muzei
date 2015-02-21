package com.arnaudpiroelle.marvelmuzei.source.command;

import com.arnaudpiroelle.marvelmuzei.R;
import com.arnaudpiroelle.marvelmuzei.core.async.DownloadAsyncTask;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.UserCommand;

import static com.arnaudpiroelle.marvelmuzei.core.inject.Injector.getContext;

public class SaveCommand extends UserCommand implements Command {

    public SaveCommand() {
        super(1, getContext().getString(R.string.command_save));
    }

    public void execute(Artwork artwork) {
        DownloadAsyncTask downloadAsyncTask = Injector.resolve(DownloadAsyncTask.class);
        downloadAsyncTask.execute(artwork);
    }


}
