package com.developer.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Mainscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        getSupportActionBar().hide();
    }
}