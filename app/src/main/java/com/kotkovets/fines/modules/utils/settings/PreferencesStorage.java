package com.kotkovets.fines.modules.utils.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by igork on 11/11/16.
 */

public class PreferencesStorage implements AppSettingsInterface {

    SharedPreferences preferences;
    final String PREFERENCES_NAME = "AppSettings";
    final String NEEDS_SHOW_GUIDE = "NeedsShowGuide";

    public PreferencesStorage(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Boolean needsShowGuide(int guideVersion) {
        return preferences.getBoolean(NEEDS_SHOW_GUIDE+guideVersion, true);
    }

    @Override
    public void setNeedsShowGuide(int version, Boolean needsShow) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(NEEDS_SHOW_GUIDE+version, needsShow);
        editor.commit();
    }
}
