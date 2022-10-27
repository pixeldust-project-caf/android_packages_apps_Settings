/*
 * Copyright (C) 2022 The PixelDust Project
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.pixeldust.settings.statusbar;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import com.pixeldust.support.preference.CustomSeekBarPreference;

@SearchIndexable
public class NetworkTrafficSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private CustomSeekBarPreference mNetTrafficSize;
    private CustomSeekBarPreference mNetTrafficAutohideThreshold;
    private CustomSeekBarPreference mNetTrafficRefreshInterval;
    private ListPreference mNetTrafficLocation;
    private ListPreference mNetTrafficMode;
    private ListPreference mNetTrafficUnits;
    private SwitchPreference mNetTrafficAutohide;
    private SwitchPreference mNetTrafficHideArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.network_traffic_settings);

        final ContentResolver resolver = getActivity().getContentResolver();

        mNetTrafficSize = (CustomSeekBarPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_FONT_SIZE);
        mNetTrafficAutohideThreshold = (CustomSeekBarPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_AUTOHIDE_THRESHOLD);
        mNetTrafficRefreshInterval = (CustomSeekBarPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_REFRESH_INTERVAL);
        mNetTrafficLocation = (ListPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_LOCATION);
        mNetTrafficLocation.setOnPreferenceChangeListener(this);
        mNetTrafficMode = (ListPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_MODE);
        mNetTrafficAutohide = (SwitchPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_AUTOHIDE);
        mNetTrafficUnits = (ListPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_UNITS);
        mNetTrafficHideArrow = (SwitchPreference)
                findPreference(Settings.Secure.NETWORK_TRAFFIC_HIDEARROW);

        int location = Settings.Secure.getIntForUser(resolver,
                Settings.Secure.NETWORK_TRAFFIC_LOCATION, 0, UserHandle.USER_CURRENT);
        updateEnabledStates(location);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mNetTrafficLocation) {
            int location = Integer.valueOf((String) newValue);
            updateEnabledStates(location);
            return true;
        }
        return false;
    }

    private void updateEnabledStates(int location) {
        final boolean enabled = location != 0;
        mNetTrafficSize.setEnabled(enabled);
        mNetTrafficMode.setEnabled(enabled);
        mNetTrafficAutohide.setEnabled(enabled);
        mNetTrafficAutohideThreshold.setEnabled(enabled);
        mNetTrafficHideArrow.setEnabled(enabled);
        mNetTrafficRefreshInterval.setEnabled(enabled);
        mNetTrafficUnits.setEnabled(enabled);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.PIXELDUST;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.network_traffic_settings);
}
