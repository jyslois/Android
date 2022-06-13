package com.android.practicenotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.RemoteInput;

public class NotiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String replyTxt = RemoteInput.getResultsFromIntent(intent).getCharSequence("message").toString();
        Log.d("kkang", "receiver...." + replyTxt);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(222);
    }
}