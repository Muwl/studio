package com.hycxinfo.yiruiyouneng.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hycxinfo.yiruiyouneng.R;

/**
 * Created by Mu on 2015/11/4.
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }).start();
    }
}
