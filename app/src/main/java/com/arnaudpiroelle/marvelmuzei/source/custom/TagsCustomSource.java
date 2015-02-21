package com.arnaudpiroelle.marvelmuzei.source.custom;

import android.content.Context;
import android.widget.Toast;
import com.arnaudpiroelle.marvelmuzei.R;
import com.arnaudpiroelle.marvelmuzei.core.api.request.QualityEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.request.TypeEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.response.Data;
import com.arnaudpiroelle.marvelmuzei.core.source.CustomSource;
import com.arnaudpiroelle.marvelmuzei.core.utils.PreferencesUtils;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource.RetryException;

import javax.inject.Inject;
import java.util.List;

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
    public Data getData(List<TypeEnum> types, List<QualityEnum> qualities) throws RetryException {
        List<String> tags = preferencesUtils.getTags();

        if(tags != null && !tags.isEmpty()) {
            return marvelCacheApiService.randomDataFromTags(types, qualities, tags);
        }

        Toast.makeText(context, R.string.no_hashtag, LENGTH_SHORT).show();
        throw new RetryException();

    }

}
