package com.gang.test.lockpattendemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by gang on 16-9-4.
 * 手写解锁activity
 */
public class SignLockerActivity extends Activity implements ILockerActivity, Handler.Callback {

    private SignaturePad mSignaturePad;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_lock_activity);

        mHandler = new Handler(this);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
            }

            @Override
            public void onClear() {
            }
        });

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

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
