package com.arnaudpiroelle.marvelmuzei.core.source;

import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.source.custom.RandomCustomSource;
import com.arnaudpiroelle.marvelmuzei.source.custom.TagsCustomSource;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class SourceRegistry {
    Map<String, CustomSource> sourceMap = new HashMap<String, CustomSource>();

    @Inject
    public SourceRegistry(RandomCustomSource randomCustomSource, TagsCustomSource tagsCustomSource) {
        sourceMap.put(randomCustomSource.getId(), randomCustomSource);
        sourceMap.put(tagsCustomSource.getId(), tagsCustomSource);

        Injector.inject(this);
    }

    public CustomSource getSource(String sourceId) {
        return sourceMap.get(sourceId);
    }
}
