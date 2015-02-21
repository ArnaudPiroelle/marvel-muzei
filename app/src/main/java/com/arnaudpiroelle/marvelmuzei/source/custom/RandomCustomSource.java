package com.arnaudpiroelle.marvelmuzei.source.custom;

import com.arnaudpiroelle.marvelmuzei.core.api.request.QualityEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.request.TypeEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.response.Data;
import com.arnaudpiroelle.marvelmuzei.core.source.CustomSource;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import javax.inject.Inject;
import java.util.List;

public class RandomCustomSource extends CustomSource {

    @Inject
    public RandomCustomSource() {

    }
    
    @Override
    public Data getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException {
        return marvelCacheApiService.randomData(types, qualities);
    }
}
