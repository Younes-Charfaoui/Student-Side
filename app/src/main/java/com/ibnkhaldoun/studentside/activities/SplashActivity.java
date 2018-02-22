package com.ibnkhaldoun.studentside.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ibnkhaldoun.studentside.R;
import com.ibnkhaldoun.studentside.Utilities.ActivityUtilities;
import com.ibnkhaldoun.studentside.Utilities.PreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);
        Class classes = WelcomeActivity.class;
        ActivityUtilities.changeStatusBarColor(getWindow());
        PreferencesManager manager = new PreferencesManager(this);
        if (manager.isLogin()) {
            classes = MainActivity.class;
        }

        Class finalClasses = classes;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, finalClasses));
            finish();
        }, SPLASH_TIME);
    }
}