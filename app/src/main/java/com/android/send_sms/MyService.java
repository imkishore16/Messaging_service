//package com.android.send_sms;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.CountDownTimer;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//
//public class MyService extends Service {
//    private static final String TAG="MyService";
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) // using thios u can have one active instance running in the foreground and you can attach multiple components to it
//    {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) { // to sspecify when we launch and when we stop
//        CountDownTimer countDownTimer = new CountDownTimer(6000,1000)
//        {
//            @Override
//            public void onTick(long millisUntilFinished){
//                Log.e(TAG,"onTick: "+millisUntilFinished/1000);
//            }
//
//            @Override
//            public void onFinish() {
//                Log.e(TAG,"onTick: ");
//
//            }
//        }.start();
//        return START_STICKY;
//    }
//}
