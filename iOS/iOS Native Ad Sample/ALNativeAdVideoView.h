//
//  ALVideoView.h
//  sdk
//
//  Created by Matt Szaro on 6/23/14.
//
//

#import "ALSdk.h"

@import AVFoundation;
@import CoreMedia;

/**
 * This is a sample video video view which is owned by an instance of ALNativeAdVideoPlayer.
 * It should not be instantiated directly.
 */
@interface ALNativeAdVideoView : UIView

@property (strong, nonatomic, readonly) AVPlayer *player;
@property (strong, nonatomic, readonly) AVPlayerLayer* playerLayer;

-(instancetype) initWithPlayer: (AVPlayer*) aPlayer;

@end
