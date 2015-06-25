package com.arnaudpiroelle.muzei.marvel.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.arnaudpiroelle.muzei.marvel.R;
import com.arnaudpiroelle.muzei.marvel.core.api.request.QualityEnum;
import com.arnaudpiroelle.muzei.marvel.core.api.request.TypeEnum;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PreferencesUtils {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferencesUtils(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public boolean isWifiEnable() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_wifiswitch_key), false);
    }

    public String getActiveSource() {
        String key = context.getString(R.string.pref_source_key);
        String defaultValue = context.getString(R.string.pref_source_defaultvalue);

        String activeSource = sharedPreferences.getString(key, defaultValue);
        if(activeSource.equals("CharacterCustomSource") || activeSource.equals("ComicCustomSource")) {
            sharedPreferences.edit().putString(context.getString(R.string.pref_source_key), defaultValue).apply();
            activeSource = defaultValue;
        }

        return activeSource;
    }

    public String getRefreshInterval() {
        String key = context.getString(R.string.pref_intervalpicker_key);
        String defaultValue = context.getString(R.string.pref_intervalpicker_defaultvalue);
        return sharedPreferences.getString(key, defaultValue);
    }

    public List<String> getTags() {
        String key = context.getString(R.string.pref_tags_values_key);
        String defaultValue = context.getString(R.string.pref_tags_default);

        List<String> tags = new ArrayList<String>();
        String prefTags = sharedPreferences.getString(key, defaultValue);

        try {
            JSONArray jsonArray = new JSONArray(prefTags);
            for (int index = 0; index < jsonArray.length(); index++) {
                tags.add(jsonArray.getString(index));
            }
        } catch (JSONException e) {

        }

        return tags;
    }

    public void setTags(List<String> tags) {
        JSONArray jsonArray = new JSONArray(tags);
        sharedPreferences.edit().putString(context.getString(R.string.pref_tags_values_key), jsonArray.toString()).apply();
    }
    
    public List<QualityEnum> getQualities(){
        List<QualityEnum> qualities = new ArrayList<>();

        qualities.add(QualityEnum.HD);

        return qualities;
    }

    public List<TypeEnum> getDataTypes() {
        List<TypeEnum> typeList = new ArrayList<>();

        String key = context.getString(R.string.pref_type_key);
        String defaultValue = context.getString(R.string.pref_type_defaultvalue);

        String types = sharedPreferences.getString(key, defaultValue);
        
        if(types.equals("CHARACTER") || types.equals("BOTH")){
            typeList.add(TypeEnum.CHARACTER);
        } 
        
        if(types.equals("COMIC") || types.equals("BOTH")){
            typeList.add(TypeEnum.COMIC);
        }
        
        return typeList;
    }
}
