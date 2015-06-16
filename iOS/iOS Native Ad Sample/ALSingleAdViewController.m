//
//  ALSingleAdViewController.m
//  iOS Native Ad Sample
//
//  Created by Matt Szaro on 6/5/15.
//  Copyright (c) 2015 AppLovin. All rights reserved.
//

#import "ALSingleAdViewController.h"
#import "ALCarouselCardView.h"

#import "ALNativeAd.h"
#import "ALNativeAdService.h"
#import "ALNativeAdLoadDelegate.h"
#import "ALSdk.h"

@interface ALSingleAdViewController () <ALNativeAdLoadDelegate, ALNativeAdPrecacheDelegate>

@property (strong, nonatomic) IBOutlet ALCarouselCardView* cardView;
@property (strong, nonatomic) ALCarouselCardState* cardState;

@end

@implementation ALSingleAdViewController

- (void) viewDidAppear:(BOOL)animated
{
    [super viewDidAppear: animated];
    self.cardView.hidden = YES;
    self.cardState = [ALCarouselCardState cardStateForSingleCard];
    [[[ALSdk shared] nativeAdService] loadNativeAdGroupOfCount: 1 andNotify: self];
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didLoadAds:(nonnull NSArray *)ads
{
    ALNativeAd* ad = [ads objectAtIndex: 0];
    [[[ALSdk shared] nativeAdService] precacheResourcesForNativeAd: ad andNotify: self];
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didPrecacheImagesForAd:(nonnull ALNativeAd *)ad
{
    [[NSOperationQueue mainQueue] addOperationWithBlock:^{
        self.cardView.hidden = NO;
        [self.cardView trackImpression];
        [self.cardView renderViewForNativeAd: ad cardState: self.cardState];
    }];
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didPrecacheVideoForAd:(nonnull ALNativeAd *)ad
{
    [[NSOperationQueue mainQueue] addOperationWithBlock:^{
        [self.cardView renderViewForNativeAd: ad cardState: self.cardState];
    }];
}

#pragma mark Failure Handling

-(void) nativeAdService:(nonnull ALNativeAdService *)service didFailToLoadAdsWithError:(NSInteger)code
{
    // In the case of a failed ad load, react appropriately - hide the view, remove it from the layout, or do whatever appropriate for your view controller.
    self.cardView.hidden = YES;
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didFailToPrecacheImagesForAd:(nonnull ALNativeAd *)ad withError:(NSInteger)errorCode
{
    // Same here - if we can't pre-cache any images, there is nothing of value to show as an ad.
    self.cardView.hidden = YES;
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didFailToPrecacheVideoForAd:(nonnull ALNativeAd *)ad withError:(NSInteger)errorCode
{
    // Video failed to load; optionally retry, or keep the ad as image-only.
}

@end
