package proyecto.jonas.volleyimp.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import proyecto.jonas.volleyimp.R;

public class NotificacionesService extends Service {

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 2000,7000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            notifyServiceActions();
        }
    };

    private void toastTest() {
        Log.d("test","MENSAJE DE PRUEBA");
    }

    private void notifyServiceActions() {

        toastTest();
/*
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");


        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0 ,myIntent, 0);


        Context context = getApplicationContext();
        Notification.Builder builder;

            builder = new Notification.Builder( context )
                        .setContentTitle("HOLA MUNDOOO")
                        .setContentText("ESTA ES UNA PRUEBA")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.notification_ask_icon)
                        .setAutoCancel(true);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            mTimer.cancel();
            timerTask.cancel();
        }catch (Exception e){
            String holis = "";
        }
    }


}
