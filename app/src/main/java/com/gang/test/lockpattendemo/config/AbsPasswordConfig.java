package com.gang.test.lockpattendemo.config;

import android.content.Context;

/**
 * Created by gang on 16-9-11.
 */
public abstract class AbsPasswordConfig implements IPasswordConfig {

    private Context mContext;

    public AbsPasswordConfig(Context context) {
        mContext = context;
    }

    protected final void setString(String key, String value) {

    }

    protected final String getString(String key) {
        return null;
    }

}
