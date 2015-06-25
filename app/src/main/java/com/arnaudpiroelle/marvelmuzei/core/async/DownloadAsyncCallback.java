package com.arnaudpiroelle.marvelmuzei.core.async;

import java.io.File;

public interface DownloadAsyncCallback {
    void doCallback(File file);
}