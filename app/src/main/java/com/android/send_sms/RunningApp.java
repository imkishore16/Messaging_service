package com.android.send_sms;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class RunningApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  // o for oreo :)
        {
//            creating the notification
            NotificationChannel channel = new NotificationChannel(
                    "running_channel",
                    "Running Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
