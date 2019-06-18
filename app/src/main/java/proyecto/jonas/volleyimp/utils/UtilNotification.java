package proyecto.jonas.volleyimp.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import proyecto.jonas.volleyimp.R;

public class UtilNotification {
    public static void sendNotification(Context context, String notificationTitle, String notificationMessage){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent resultIntent  = new Intent( context, context.getClass() );
        PendingIntent resultPendingIntent = PendingIntent.getActivity( context, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT );

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "M_CH_ID")
                .setSound( alarmSound )
                .setAutoCancel(true)
                .setContentIntent( resultPendingIntent )
                .setDefaults( NotificationCompat.DEFAULT_ALL )
                .setSmallIcon( R.drawable.ic_launcher_app )
                .setContentTitle(notificationTitle )
                .setContentText( notificationMessage );

        NotificationManager notificationManager = (NotificationManager) context.getSystemService( context.getApplicationContext().NOTIFICATION_SERVICE );
        notificationManager.notify( 1,notificationBuilder.build() );
    }

   public static void openAppOnNotificationClick(Context context, String notificationTitle, String notificationMessage){
       Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       Intent resultIntent = new Intent(context, context.getClass());

       PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
               0, resultIntent,
               PendingIntent.FLAG_CANCEL_CURRENT);

       NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "M_CH_ID")
               .setSound( alarmSound )
               .setAutoCancel(true)
               .setDefaults( NotificationCompat.DEFAULT_ALL )
               .setSmallIcon( R.drawable.ic_launcher_app )
               .setContentTitle(notificationTitle )
               .setContentText( notificationMessage )
               .setContentIntent(resultPendingIntent);


       NotificationManager notificationManager = (NotificationManager) context.getSystemService( context.getApplicationContext().NOTIFICATION_SERVICE );
       notificationManager.notify( 0,notificationBuilder.build() );

   }

}
