package com.applovin.nativeadsdemo;

import android.app.Activity;
import android.os.Bundle;

import com.applovin.carouselui.AppLovinCarouselView;

/**
 * This activity simply inflates a default XML file that contains a carousel view, which will load its own ads into it.
 */
public class CarouselActivity extends Activity {

    private AppLovinCarouselView carouselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        carouselView = (AppLovinCarouselView) findViewById(R.id.carouselView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carouselView.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        carouselView.onStop(this);
    }
}
