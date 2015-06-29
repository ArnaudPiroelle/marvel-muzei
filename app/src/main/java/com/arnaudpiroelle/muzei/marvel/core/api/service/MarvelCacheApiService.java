package com.arnaudpiroelle.muzei.marvel.core.api.service;

import com.arnaudpiroelle.muzei.marvel.core.api.request.QualityEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.request.TypeEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.response.Data;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MarvelCacheApiService {
    @GET("/api/rest/random")
    Observable<Data> randomData(@Query("types") List<TypeEnum> types,
                    @Query("qualities") List<QualityEnum> qualities);
    
    @GET("/api/rest/random/tags")
    Observable<Data> randomDataFromTags(@Query("types") List<TypeEnum> types,
                                   @Query("qualities") List<QualityEnum> qualities,
                                   @Query("tags") List<String> tags);

}
