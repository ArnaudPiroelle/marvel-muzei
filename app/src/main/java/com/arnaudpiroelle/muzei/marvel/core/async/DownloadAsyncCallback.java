package com.arnaudpiroelle.muzei.marvel.core.async;

import java.io.File;

public interface DownloadAsyncCallback {
    void doCallback(File file);
}