package com.android.send_sms;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class RunningService extends Service {
    private static final String TAG="MyService";
    private final Handler handler = new Handler();
    private final String phoneNumber = "9626044841";
    private final String message = "noob de!!";
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
        Notification notification = new NotificationCompat.Builder(this, "running_channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Active")
                .setContentText("elapsed time")
                .build();
        startForeground(1, notification);

        handler.postDelayed(sendMessageTask, 10 * 1000); // Initial delay: 2 minutes
    }

    private final Runnable sendMessageTask = new Runnable() {
        @Override
        public void run() {
            sendMessage();
            handler.postDelayed(this, 10 * 1000);
        }
    };

    // main logic
    // add async func to read endpoints , create list of numbers and corresp msgs
    private void sendMessage() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            showToast("SMS sent successfully");
        } catch (Exception e) {
            showToast("Failed to send SMMS");
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}