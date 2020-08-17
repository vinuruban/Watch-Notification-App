package com.example.watchnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up notification manager and channel
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("default", "test", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(notificationChannel);

        //create intent - pending intent has a request code we can work with too
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //build notification
        Notification.Builder builder = new Notification.Builder(this, "default")
                .setContentTitle("Alert")
                .setContentText("You have a notification")
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentIntent(pendingIntent); //when clicked on the notification, it will take me back to the app

        //finally send notification
        notificationManager.notify(0, builder.build());

        // Enables Always-on
        setAmbientEnabled();
    }
}
