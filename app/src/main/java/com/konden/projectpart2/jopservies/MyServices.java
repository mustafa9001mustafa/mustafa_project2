package com.konden.projectpart2.jopservies;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.konden.projectpart2.R;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.ui.SplashScreenApp;

public class MyServices extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        addNotification();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i("TAG", "onStopJob: ");
        return false;
    }

    private void addNotification() {
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(FinalContract.Notification_id, FinalContract.MyNotification, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(getBaseContext(), SplashScreenApp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), FinalContract.Notification_id);

        builder.setContentTitle(getResources().getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setVibrate(new long[]{2000, 500, 5000})
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.big_text_notify)))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat compat = NotificationManagerCompat.from(getBaseContext());
        compat.notify(1, builder.build());

    }
}