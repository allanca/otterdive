//
//  FaceDetectAppDelegate.m
//  FaceDetect
//
//  Created by Alasdair Allan on 15/12/2009.
//  Copyright University of Exeter 2009. All rights reserved.
//

#import "FaceDetectAppDelegate.h"
#import "FaceDetectViewController.h"

@implementation FaceDetectAppDelegate

@synthesize window;
@synthesize viewController;
@synthesize facebook;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];

	NSArray* permissions =  [[NSArray arrayWithObjects:
							  @"email", @"user_photo_video_tags",
							  @"friends_photo_video_tags", @"user_photos",
							  @"friends_photos", nil] retain];
	facebook = [[Facebook alloc] initWithAppId:@"171380946241404"];
	[facebook authorize:permissions delegate:self];	
}

- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
	
    return [facebook handleOpenURL:url]; 
}

- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
