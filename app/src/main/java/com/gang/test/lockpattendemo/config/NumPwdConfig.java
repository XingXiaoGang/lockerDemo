package com.gang.test.lockpattendemo.config;

import android.content.Context;

/**
 * Created by gang on 16-9-11.
 */
public class NumPwdConfig extends StringPwdConfig {

    public NumPwdConfig(Context context) {
        super(context);
    }

    @Override
    public void setPwd(String key, Object value) {

    }

    @Override
    public Object getPwd(String key) {
        return null;
    }
}
