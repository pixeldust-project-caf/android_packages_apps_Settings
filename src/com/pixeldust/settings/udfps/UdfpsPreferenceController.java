/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.pixeldust.settings.udfps;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;

import com.android.settings.core.BasePreferenceController;
import com.android.settings.Utils;

import java.util.List;

public class UdfpsPreferenceController extends BasePreferenceController {

    public static final String KEY = "udfps_settings";
    private FingerprintManager mFingerprintManager;
    private List<FingerprintSensorPropertiesInternal> mSensorProperties;

    public UdfpsPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
        mFingerprintManager = Utils.getFingerprintManagerOrNull(context);

        if (mFingerprintManager != null)
            mSensorProperties = mFingerprintManager.getSensorPropertiesInternal();

    }

    public UdfpsPreferenceController(Context context) {
        this(context, KEY);
    }

    private boolean isUdfps() {
        for (FingerprintSensorPropertiesInternal prop : mSensorProperties) {
            if (prop.isAnyUdfpsType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getAvailabilityStatus() {
        if (!isUdfps()){
            return UNSUPPORTED_ON_DEVICE;
        }
        if (mFingerprintManager != null &&
            (!mFingerprintManager.isHardwareDetected() || !mFingerprintManager.hasEnrolledFingerprints())) {
            return UNSUPPORTED_ON_DEVICE;
        }
        return AVAILABLE;
    }
}
