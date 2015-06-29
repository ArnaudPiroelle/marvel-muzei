package com.arnaudpiroelle.muzei.marvel.source.custom;

import android.content.Context;
import android.widget.Toast;

import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.api.request.QualityEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.request.TypeEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.response.Data;
import com.arnaudpiroelle.muzei.marvel.core.source.CustomSource;
import com.arnaudpiroelle.muzei.marvel.core.utils.PreferencesUtils;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.widget.Toast.LENGTH_SHORT;

public class TagsCustomSource extends CustomSource {
    @Inject
    PreferencesUtils preferencesUtils;

    @Inject
    Context context;

    @Inject
    public TagsCustomSource() {
        
    }
    
    @Override
    public Observable<Data> getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException {
        List<String> tags = preferencesUtils.getTags();

        if(tags != null && !tags.isEmpty()) {
            return marvelCacheApiService.randomDataFromTags(types, qualities, tags);
        }

        Toast.makeText(context, R.string.no_hashtag, LENGTH_SHORT).show();
        throw new RetryException();

    }

}
