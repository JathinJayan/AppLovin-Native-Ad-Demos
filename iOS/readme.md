iOS Native Ad Demo
==================

This repository contains a reference implementation of a native ad carousel, as well as a few simple view controllers demonstrating their use.

## Reference Implementations

ALCarouselView provides a drop-in view which can render an array of ALNativeAd objects. If you're looking for a simple yet elegant implantation of a native ad view, this swipe-able, fully customizable UIView can be used as a drop-in component in your application.

Conceptually, an ALCarouselView manages a set of ALCarouselCardView objects, which each individually represent a single native ad. For developers who prefer single, self-contained ad views over our carousel metaphor, it is entirely possible to use a single ALCarouselCardView _as_ your full integration. This is demonstrated within _ALSingleAdViewController_.

## Obtaining & Linking the AppLovin SDK

In order to build the demo app, you'll need to download a copy of the [latest version of the AppLovin iOS SDK, add your personal SDK key to the Info.plist,] (http://applovin.com/integration) and drag it into the Xcode project.