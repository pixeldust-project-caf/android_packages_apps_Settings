/*
 * Copyright (C) 2023 The PixelDust Project
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.pixeldust.settings.tweaks;

import android.os.Bundle;
import android.os.SystemProperties;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

@SearchIndexable
public class GeneralSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String KEY_SPOOF = "use_photos_spoof";
    private static final String SYS_SPOOF = "persist.sys.pixelprops.gphotos";

    private SwitchPreference mSpoof;

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.PIXELDUST;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_settings);

        mSpoof = (SwitchPreference) findPreference(KEY_SPOOF);
        mSpoof.setChecked(SystemProperties.getBoolean(SYS_SPOOF, true));
        mSpoof.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mSpoof) {
            String value = ((Boolean) objValue) ? "true" : "false";
            SystemProperties.set(SYS_SPOOF, value);
            Toast.makeText(getActivity(),
                    (R.string.photos_spoof_toast), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.general_settings);
}
