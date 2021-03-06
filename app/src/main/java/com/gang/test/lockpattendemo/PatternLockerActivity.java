package com.gang.test.lockpattendemo;

import android.app.Activity;
import android.os.Bundle;

import com.gang.test.lockpattendemo.config.IPasswordConfig;
import com.gang.test.lockpattendemo.config.PatternPwdConfig;

/**
 * Created by gang on 16-8-14.
 */
public class PatternLockerActivity extends Activity implements ILockerActivity {

    private int[] mPassword;
    private IPasswordConfig mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_lock_activity);
        mConfig = new PatternPwdConfig(PatternLockerActivity.this);
    }

    @Override
    public Object generatePassword() {
        return mConfig.getPwd(IPasswordConfig.KEY_PATTERN);
    }

    @Override
    public int generateMaxFailTimes() {
        return 0;
    }

    @Override
    public void onUnLockFial(LockEvent lockEvent) {

    }

    @Override
    public void onUnlock() {

    }
}
