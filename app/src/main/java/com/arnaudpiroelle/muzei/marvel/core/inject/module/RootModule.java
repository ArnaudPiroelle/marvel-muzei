package com.arnaudpiroelle.muzei.marvel.core.inject.module;

import dagger.Module;

@Module(includes = {AndroidModule.class, ApplicationModule.class})
public class RootModule {
}
