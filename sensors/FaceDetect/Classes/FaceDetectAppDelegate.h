//
//  FaceDetectAppDelegate.h
//  FaceDetect
//
//  Created by Alasdair Allan on 15/12/2009.
//  Copyright University of Exeter 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FaceDetectViewController;

@interface FaceDetectAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    FaceDetectViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet FaceDetectViewController *viewController;

@end

