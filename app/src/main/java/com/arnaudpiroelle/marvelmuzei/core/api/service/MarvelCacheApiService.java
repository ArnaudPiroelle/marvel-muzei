package com.arnaudpiroelle.marvelmuzei.core.api.service;

import com.arnaudpiroelle.marvelmuzei.core.api.request.QualityEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.request.TypeEnum;
import com.arnaudpiroelle.marvelmuzei.core.api.response.Data;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public interface MarvelCacheApiService {
    @GET("/api/rest/random")
    public Data randomData(@Query("types") List<TypeEnum> types,
                           @Query("qualities") List<QualityEnum> qualities);
    
    @GET("/api/rest/random/tags")
    public Data randomDataFromTags(@Query("types") List<TypeEnum> types,
                                   @Query("qualities") List<QualityEnum> qualities,
                                   @Query("tags") List<String> tags);

}
