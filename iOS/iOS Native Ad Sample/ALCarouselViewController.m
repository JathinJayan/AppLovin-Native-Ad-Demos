//
//  ALCarouselViewController.m
//  iOS Native Ad Sample
//
//  Created by Matt Szaro on 6/5/15.
//  Copyright (c) 2015 AppLovin. All rights reserved.
//

#import "ALCarouselViewController.h"
#import "ALCarouselView.h"
#import "ALNativeAdLoadDelegate.h"

@interface ALCarouselViewController () <ALNativeAdLoadDelegate>

@property (strong, nonatomic) IBOutlet ALCarouselView *carouselView;

@end

@implementation ALCarouselViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.carouselView.loadDelegate = self;
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didLoadAds:(nonnull NSArray *)ads
{
    // Show the ads to the user only upon successful load.
    self.carouselView.hidden = NO;
}

-(void) nativeAdService:(nonnull ALNativeAdService *)service didFailToLoadAdsWithError:(NSInteger)code
{
    // In the case of a failed ad load, react appropriately - hide the view, remove it from the layout, or do whatever appropriate for your view controller.
    self.carouselView.hidden = YES;
}

@end
