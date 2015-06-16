package com.applovin.nativeadsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchCarouselActivity(final View caller) {
        final Intent launchIntent = new Intent(this, CarouselActivity.class);
        startActivity(launchIntent);
    }

    public void launchSingleAdActivity(final View caller) {
        final Intent launchIntent = new Intent(this, ProgrammaticLoadActivity.class);
        startActivity(launchIntent);
    }

}
