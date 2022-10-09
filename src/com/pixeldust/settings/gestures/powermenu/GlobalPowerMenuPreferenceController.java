/*
 * Copyright (C) 2022 The PixelDust Project
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.pixeldust.settings.gestures.powermenu;

import android.content.Context;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class GlobalPowerMenuPreferenceController extends BasePreferenceController {

    private static final String PREF_KEY_POWERMENU = "gesture_global_powermenu_summary";

    public GlobalPowerMenuPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public CharSequence getSummary() {
        return mContext.getText(R.string.powermenu_summary);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public String getPreferenceKey() {
        return PREF_KEY_POWERMENU;
    }
}
