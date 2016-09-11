package com.gang.test.lockpattendemo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.gang.test.lockpattendemo.config.IPasswordConfig;
import com.gang.test.lockpattendemo.config.NumPwdConfig;

/**
 * Created by gang on 16-9-11.
 */
public class PasswordLockerDialog extends Dialog implements ILockerActivity {

    private Activity mLockeActivity;
    private IPasswordConfig mPasswordConfig;

    public PasswordLockerDialog(Activity context) {
        super(context);
        this.mLockeActivity = context;
        this.mPasswordConfig = new NumPwdConfig(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object generatePassword() {
        return mPasswordConfig.getPwd(IPasswordConfig.KEY_NUM);
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
