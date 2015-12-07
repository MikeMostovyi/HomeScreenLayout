package com.layouttest.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.layouttest.layout.HomeScreenLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HomeScreenLayout mHomeScreenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeScreenLayout = (HomeScreenLayout) findViewById(R.id.home_screen);
        mHomeScreenLayout.setItemCount(5);

        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_25).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_5:
                mHomeScreenLayout.setItemCount(5);
                break;
            case R.id.button_25:
                mHomeScreenLayout.setItemCount(25);
                break;
        }
    }
}
