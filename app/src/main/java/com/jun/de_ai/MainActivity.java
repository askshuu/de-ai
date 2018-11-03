package com.jun.de_ai;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        UnitFragment unitFragment = new UnitFragment();
        fragmentTransaction.replace(android.R.id.content, unitFragment);
        fragmentTransaction.commit();
    }
}
