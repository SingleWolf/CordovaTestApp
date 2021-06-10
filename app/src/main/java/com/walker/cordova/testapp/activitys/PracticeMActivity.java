package com.walker.cordova.testapp.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.walker.cordova.testapp.R;
import com.walker.cordova.testapp.activitys.base.BaseActivity;

/**
 *
 */
public class PracticeMActivity extends BaseActivity {

    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_m);

        iv_image = findViewById(R.id.iv_image);
    }

    public void testClick(View v){

    }
}
