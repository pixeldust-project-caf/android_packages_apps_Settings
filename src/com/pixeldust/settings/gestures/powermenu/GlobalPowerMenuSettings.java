/*
 * Copyright (C) 2022 The PixelDust Project
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.pixeldust.settings.gestures.powermenu;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import java.util.Arrays;
import java.util.List;

@SearchIndexable
public class GlobalPowerMenuSettings extends DashboardFragment {

    private static final String TAG = "GlobalPowerMenuSettings";

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.PIXELDUST;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.global_powermenu_settings;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.global_powermenu_settings);
}
