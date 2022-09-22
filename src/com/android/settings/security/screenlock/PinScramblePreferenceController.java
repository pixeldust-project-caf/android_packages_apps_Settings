package com.android.settings.security.screenlock;

import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

public class PinScramblePreferenceController extends AbstractPreferenceController
        implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {

    private static final String KEY_SCRAMBLE_PIN_LAYOUT = "scramble_pin_layout";

    public PinScramblePreferenceController(Context context) {
        super(context);
    }

    private int getScramblePinSettings() {
        return Settings.Secure.getIntForUser(
                mContext.getContentResolver(),
                Settings.Secure.SCRAMBLE_PIN_LAYOUT, 0,
                UserHandle.USER_CURRENT);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getPreferenceKey() {
        return KEY_SCRAMBLE_PIN_LAYOUT;
    }

    @Override
    public void updateState(Preference preference) {
        ((TwoStatePreference) preference).setChecked(getScramblePinSettings() == 1);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        Settings.Secure.putIntForUser(mContext.getContentResolver(),
                Settings.Secure.SCRAMBLE_PIN_LAYOUT,
                (Boolean) value ? 1 : 0, UserHandle.USER_CURRENT);
        return true;
    }
}
