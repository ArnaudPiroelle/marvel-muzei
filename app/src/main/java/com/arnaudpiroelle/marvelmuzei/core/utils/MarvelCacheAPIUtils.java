package com.arnaudpiroelle.marvelmuzei.core.utils;

import com.arnaudpiroelle.marvelmuzei.core.api.service.MarvelCacheApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static retrofit.RestAdapter.LogLevel.BASIC;

public class MarvelCacheAPIUtils {
    public static final Gson gson = new GsonBuilder()
            .setDateFormat(Constants.DATE_FORMAT)
            .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(Constants.BASE_URL)
            .setConverter(new GsonConverter(gson))
            .setLog(new AndroidLog(Constants.APP_TAG))
            .setLogLevel(BASIC)
            .build();

    public static MarvelCacheApiService getService() {
        return restAdapter.create(MarvelCacheApiService.class);
    }
}
