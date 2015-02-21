package com.arnaudpiroelle.marvelmuzei.core.source;

import com.arnaudpiroelle.marvelmuzei.core.api.request.QualityEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.request.TypeEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.response.Data;
import com.arnaudpiroelle.marvelmuzei.core.api.service.MarvelCacheApiService;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import javax.inject.Inject;
import java.util.List;


public abstract class CustomSource {

    @Inject
    protected MarvelCacheApiService marvelCacheApiService;

    public String getId(){
        return this.getClass().getSimpleName();
    }

    public abstract Data getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException;
}
