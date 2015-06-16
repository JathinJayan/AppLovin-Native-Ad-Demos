package com.applovin.nativeadsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.applovin.carouselui.AppLovinCarouselView;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

import java.util.List;

/**
 * This activity explicitly loads a single native ad, and renders it into a programmatically instantiated carousel view.
 */
public class ProgrammaticLoadActivity extends Activity {

    private LinearLayout singleCardContainer;
    private AppLovinCarouselView carouselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_card);

        singleCardContainer = (LinearLayout) findViewById(R.id.singleViewContainer);

        final AppLovinSdk sdk = AppLovinSdk.getInstance(this);
        final AppLovinNativeAdService nativeAdService = sdk.getNativeAdService();

        final Activity thisRef = this;
        nativeAdService.loadNativeAds(1, new AppLovinNativeAdLoadListener() {
            @Override
            public void onNativeAdsLoaded(final List  /* <AppLovinNativeAd> */ nativeAds) {

                // When native ad is loaded, precache its resources.
                // We don't want to show an impression until at least the ad's images are ready.

                final AppLovinNativeAd nativeAd = (AppLovinNativeAd) nativeAds.get(0);
                nativeAdService.precacheResources(nativeAd, new AppLovinNativeAdPrecacheListener() {
                    @Override
                    public void onNativeAdImagesPrecached(final AppLovinNativeAd nativeAd) {
                        thisRef.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // The ad's images are precached at this point, so we attach a carousel view and render this ad into it.
                                singleCardContainer.removeAllViews();

                                carouselView = new AppLovinCarouselView(thisRef);
                                carouselView.setNativeAds(nativeAds);
                                carouselView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppLovinSdkUtils.dpToPx(thisRef, 450)));
                                singleCardContainer.addView(carouselView);
                            }
                        });
                    }

                    @Override
                    public void onNativeAdVideoPreceached(final AppLovinNativeAd nativeAd) {
                        // Video pre-caching is handled in our sample view by InlineCarouselCardView.
                        // However, in a simple custom integration, you may use this callback to fade in a VideoView.
                    }

                    @Override
                    public void onNativeAdImagePrecachingFailed(final AppLovinNativeAd nativeAd, int i) {
                        hideCardView();
                    }

                    @Override
                    public void onNativeAdVideoPrecachingFailed(final AppLovinNativeAd nativeAd, int i) {
                        hideCardView();
                    }
                });
            }

            @Override
            public void onNativeAdsFailedToLoad(int i) {
                hideCardView();
            }
        });
    }

    private void hideCardView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                singleCardContainer.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (carouselView != null) {
            carouselView.onResume(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (carouselView != null) {
            carouselView.onStop(this);
        }
    }
}
