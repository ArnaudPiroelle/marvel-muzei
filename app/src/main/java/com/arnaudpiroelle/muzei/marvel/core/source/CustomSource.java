package com.arnaudpiroelle.muzei.marvel.core.source;

import com.arnaudpiroelle.muzei.marvel.core.api.request.QualityEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.request.TypeEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.response.Data;
import com.arnaudpiroelle.muzei.marvel.core.api.service.MarvelCacheApiService;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import java.util.List;

import javax.inject.Inject;


public abstract class CustomSource {

    @Inject
    protected MarvelCacheApiService marvelCacheApiService;

    public String getId(){
        return this.getClass().getSimpleName();
    }

    public abstract Data getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException;
}
