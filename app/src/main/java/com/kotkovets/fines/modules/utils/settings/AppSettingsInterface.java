package com.kotkovets.fines.modules.utils.settings;

/**
 * Created by igork on 11/11/16.
 */

public interface AppSettingsInterface {
    void setNeedsShowGuide(int version, Boolean needsShow);
    Boolean needsShowGuide(int version);
}
