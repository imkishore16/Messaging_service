package com.android.send_sms;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class RunningService extends Service {
    private static final String TAG="MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) // using this u can have one active instance running in the foreground and you can attach multiple components to it
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case "START":
                    start();
                    break;
                case "STOP":
                    stopSelf();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void start() {
        Notification notification = new NotificationCompat.Builder(this, "running_channel") // to create custom notification
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Active")
                .setContentText("elapsed time")
                .build();
        startForeground(1, notification);
    }

}
