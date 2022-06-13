package com.android.practicenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.graphics.drawable.IconCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.android.practicenotification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    NotificationManager manager;
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bigPictureButton.setOnClickListener(this);
        binding.progressButton.setOnClickListener(this);
        binding.messageButton.setOnClickListener(this);
        binding.remoteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 채널 생성
            String channelId = "one-channel";
            String channelName = "My Channel One";
            String ChannelDescription = "My Channel One Description";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(ChannelDescription);

            // 각종 채널에 대한 설정
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[] {100, 200, 300});
            manager.createNotificationChannel(channel);
            // 채널이 등록된 빌더
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("메시지 도착");
        builder.setContentText("안녕하세요");
        builder.setAutoCancel(true);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.noti_large);
        builder.setLargeIcon(largeIcon);

        if (v == binding.bigPictureButton) {
            // BigPictureStyle
            Bitmap bigPicture = BitmapFactory.decodeResource(getResources(), R.drawable.noti_big);
            NotificationCompat.BigPictureStyle bigStyle = new NotificationCompat.BigPictureStyle(builder);
            bigStyle.bigPicture(bigPicture);
            builder.setStyle(bigStyle);
        } else if (v == binding.progressButton) {
            // Progress 알림
            Thread t = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    builder.setAutoCancel(false);
                    builder.setOngoing(true);
                    builder.setProgress(10, i, false);
                    manager.notify(222, builder.build());
                    if (i >= 10) {
                        manager.cancel(222);
                    }
                    SystemClock.sleep(1000);
                }
            });
            t.start();
        } else if (v == binding.messageButton) {
            // MessageStyle
            // androidx.core.app.Person 이용
            Person sender1 = new Person.Builder().setName("kkang").setIcon(IconCompat.createWithResource(this, R.drawable.person1)).build();
            Person sender2 = new Person.Builder().setName("kim").setIcon(IconCompat.createWithResource(this, R.drawable.person2)).build();

            NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("hello", System.currentTimeMillis(), sender2);
            NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle(sender1).addMessage("world", System.currentTimeMillis(), sender1).addMessage(message);
            builder.setStyle(style);
        } else if (v == binding.remoteButton) {
            // RemoteInput
            String replyKey = "message";
            String replyLabel = "답장";
            // android.core.app.RemoteInput 이용
            RemoteInput.Builder remoteBuilder = new RemoteInput.Builder(replyKey);
            remoteBuilder.setLabel(replyLabel);
            RemoteInput remoteInput = remoteBuilder.build();

            Intent remoteIntent = new Intent(this, NotiReceiver.class);
            PendingIntent remotePendingIntent = PendingIntent.getBroadcast(this, 20, remoteIntent, PendingIntent.FLAG_MUTABLE);
            NotificationCompat.Action.Builder remoteActionBuilder = new NotificationCompat.Action.Builder(android.R.drawable.stat_notify_chat, "답장하기", remotePendingIntent);
            remoteActionBuilder.addRemoteInput(remoteInput);
            NotificationCompat.Action remoteAction = remoteActionBuilder.build();;
            builder.addAction(remoteAction);
        }
        manager.notify(222, builder.build());
    }

}