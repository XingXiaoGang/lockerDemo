package com.gang.test.lockpattendemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gang.test.lockpattendemo.config.IPasswordConfig;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Created by gang on 16-9-4.
 * 手写解锁activity
 */
public class SignLockerActivity extends Activity implements ILockerActivity, Handler.Callback {

    private SignaturePad mSignaturePad;
    private Handler mHandler;
    private TessBaseAPI tessBaseAPI;
    private IPasswordConfig mConfig;

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
                tessBaseAPI.setImage(mSignaturePad.getSignatureBitmap());
                Log.d("test.SignLockerActivity", "onSigned: " + tessBaseAPI.getUTF8Text());
            }

            @Override
            public void onClear() {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tessBaseAPI == null) {
            tessBaseAPI = new TessBaseAPI();
            tessBaseAPI.init(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/tesseract/", "eng");
        }
    }

    @Override
    public Object generatePassword() {
        return mConfig.getPwd(IPasswordConfig.KEY_TEXT);
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

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
