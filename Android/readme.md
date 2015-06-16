Android Native Ad Demo
==================

This repository contains a reference implementation of a native ad carousel, as well as a few simple view controllers demonstrating their use.

## Reference Implementations

AppLovinCarouselView provides a drop-in view which can render an array of ALNativeAd objects. If you're looking for a simple yet elegant implantation of a native ad view, this swipe-able, fully customizable View can be used as a drop-in component in your application.

Conceptually, a carousel view is build on top of a customized ViewPager which center-locks its contents; each individual view contained within it is an instance of InlineCarouselCardView, which represents a single native ad.

## Obtaining & Linking the AppLovin SDK

In order to build the demo app, you'll need to download a copy of the [latest version of the AppLovin Android SDK, add your personal SDK key to the manifest,] (http://applovin.com/integration) and drag it into the Xcode project.