//package com.android.send_sms;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.Telephony;
//import android.telephony.SmsManager;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import android.os.AsyncTask;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final int PERMISSION_REQUEST_SEND_SMS = 1;
//    private static final int PERMISSION_POST_NOTIFICATION = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button sendButton = findViewById(R.id.sendButton);
//
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkSendSMSPermission()) {
//                    sendMessage();
//                } else
//                    requestSendSMSPermission();
//            }
//        });
//    }
//
//    private boolean checkSendSMSPermission() {
//        return ContextCompat.checkSelfPermission(
//                this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestSendSMSPermission() {
//        ActivityCompat.requestPermissions(
//                this,
//                new String[]{Manifest.permission.SEND_SMS},
//                PERMISSION_REQUEST_SEND_SMS
//
//        );
//    }
//    private static final int REQUEST_SMS_PERMISSION = 1;
//
//    private void checkAndRequestPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
//                    REQUEST_SMS_PERMISSION);
//        }
//        else
//            sendMessage();
//    }
//
//    private void sendMessage() {
//        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
//        EditText messageEditText = findViewById(R.id.messageEditText);
//
//        String phoneNumber = phoneNumberEditText.getText().toString();
//        String message = messageEditText.getText().toString();
//
//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
//            showToast("SMS sent successfully");
//        } catch (Exception e) {
//            showToast("Failed to send SMMS");
//            e.printStackTrace();
//        }
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(
//            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                sendMessage();
//            }
//        }
//    }
//}
//

package com.android.send_sms;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.ComponentActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    0
            );
        }

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForegroundService();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopForegroundService();
            }
        });
    }

    private void startForegroundService() {
        Intent intent = new Intent(this, RunningService.class);
        intent.setAction("START");
        startService(intent);
    }

    private void stopForegroundService() {
        Intent intent = new Intent(this, RunningService.class);
        intent.setAction("STOP");
        startService(intent);
    }
}

