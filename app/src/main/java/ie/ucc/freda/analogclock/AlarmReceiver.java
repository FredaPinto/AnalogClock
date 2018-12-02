package ie.ucc.freda.analogclock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManagerCompat notificationManager;
    Notification myNotification;


    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmActivity a = new AlarmActivity();

        Intent myIntent = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(""));
            context.startForegroundService(intent);
        }
Log.d("Debug","on Receive");

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("Debug","on Receive intent");
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, "channel_1");

        notificationBuilder
                .setContentTitle("Alarm")
                .setContentText(a.getAlarmTime())
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent);

        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notificationBuilder.build());

        Ringtone rt = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)); // also try RingtoneManager.TYPE_NOTIFICATION
        rt.play();
        try {
            Thread.sleep(10000);
            rt.stop();
        }
        catch (InterruptedException e)    {
            e.printStackTrace();
        }
        catch (Exception e)    {
            e.printStackTrace();
        }

    }

}