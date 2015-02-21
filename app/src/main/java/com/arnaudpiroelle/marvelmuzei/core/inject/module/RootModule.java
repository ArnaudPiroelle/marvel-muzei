package com.arnaudpiroelle.marvelmuzei.core.inject.module;

import dagger.Module;

@Module(includes = {AndroidModule.class, ApplicationModule.class})
public class RootModule {
}
