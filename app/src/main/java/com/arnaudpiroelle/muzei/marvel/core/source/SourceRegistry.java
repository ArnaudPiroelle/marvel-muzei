package com.arnaudpiroelle.muzei.marvel.core.source;

import com.arnaudpiroelle.muzei.marvel.core.inject.Injector;
import com.arnaudpiroelle.muzei.marvel.source.custom.RandomCustomSource;
import com.arnaudpiroelle.muzei.marvel.source.custom.TagsCustomSource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
