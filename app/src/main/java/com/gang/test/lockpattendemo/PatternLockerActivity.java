package com.gang.test.lockpattendemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by gang on 16-8-14.
 */
public class PatternLockerActivity extends Activity implements ILockerActivity {

    private int[] mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_lock_activity);
    }

    @Override
    public Object generatePassword() {
        return null;
    }

    @Override
    public int generateMaxFailTimes() {
        return 0;
    }

    @Override
    public void onUnLockFial(LockEvent lockEvent) {

    }
}
