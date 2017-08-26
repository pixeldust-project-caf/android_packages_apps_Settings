/*
 * Copyright (C) 2017 The Pixel Dust Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.support.annotation.VisibleForTesting;

import com.android.settings.R;

public class PDBuildDateDialogController {

    @VisibleForTesting
    private static final int PD_BUILD_VALUE_ID = R.id.pd_build_date;
    private static final String PD_BUILD = "pd_build_date";
    private static final String PD_PROP = "ro.build.date";

    private final FirmwareVersionDialogFragment mDialog;
    private final Context mContext;

    public PDBuildDateDialogController(FirmwareVersionDialogFragment dialog) {
        mDialog = dialog;
        mContext = dialog.getContext();
    }

    public void initialize() {
         mDialog.setText(PD_BUILD_VALUE_ID, SystemProperties.get(PD_PROP,
                mContext.getResources().getString(R.string.device_info_default)));
    }
}
