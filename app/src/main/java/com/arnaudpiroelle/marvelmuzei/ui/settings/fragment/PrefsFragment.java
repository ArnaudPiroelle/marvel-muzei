package com.arnaudpiroelle.marvelmuzei.ui.settings.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.view.View;
import com.arnaudpiroelle.marvelmuzei.BuildConfig;
import com.arnaudpiroelle.marvelmuzei.R;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.core.utils.PreferencesUtils;
import com.arnaudpiroelle.marvelmuzei.core.utils.TrackerUtils;

import javax.inject.Inject;
import java.util.Calendar;

import static com.arnaudpiroelle.marvelmuzei.core.utils.Constants.TAG_SOURCE;

public class PrefsFragment extends PreferenceFragment implements OnPreferenceClickListener, OnPreferenceChangeListener {

    @Inject
    PreferencesUtils preferencesUtils;

    Preference sourcePreference;
    Preference aboutPreference;
    Preference tagsPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Injector.inject(this);
        
        aboutPreference = findPreference(getString(R.string.pref_about_key));
        tagsPreference = findPreference(getString(R.string.pref_tags_key));
        sourcePreference = findPreference(getString(R.string.pref_source_key));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setFitsSystemWindows(true);

        String version = getString(R.string.pref_about_summary, BuildConfig.VERSION_NAME,
                Calendar.getInstance().get(Calendar.YEAR));
        aboutPreference.setSummary(version);
        sourcePreference.setOnPreferenceChangeListener(this);

        tagsPreference.setOnPreferenceClickListener(this);

    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.title_activity_settings);

        TrackerUtils.sendScreen("SettingsFragment");
        
        enableTagsPrefBySource(preferencesUtils.getActiveSource());
        
        super.onResume();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (tagsPreference.equals(preference)) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pref_content, new MyTagsFragment())
                    .addToBackStack("prefs")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newPreferenceValue) {
        if (sourcePreference.equals(preference)) {
            enableTagsPrefBySource((String) newPreferenceValue);
            return true;
        }
        
        return false;
    }

    private void enableTagsPrefBySource(String source) {
        if (source.equals(TAG_SOURCE)) {
            tagsPreference.setEnabled(true);
        } else {
            tagsPreference.setEnabled(false);
        }
    }
}
