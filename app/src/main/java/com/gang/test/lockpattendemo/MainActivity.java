package com.gang.test.lockpattendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_pattern).setOnClickListener(this);
        findViewById(R.id.start_sign).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_pattern: {
                Intent intent = new Intent(MainActivity.this, PatternLockerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.start_sign: {
                Intent intent = new Intent(MainActivity.this, SignLockerActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
