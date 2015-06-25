package com.arnaudpiroelle.muzei.marvel.source.custom;

import com.arnaudpiroelle.muzei.marvel.core.api.request.QualityEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.request.TypeEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.response.Data;
import com.arnaudpiroelle.muzei.marvel.core.source.CustomSource;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import java.util.List;

import javax.inject.Inject;

public class RandomCustomSource extends CustomSource {

    @Inject
    public RandomCustomSource() {

    }
    
    @Override
    public Data getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException {
        return marvelCacheApiService.randomData(types, qualities);
    }
}
