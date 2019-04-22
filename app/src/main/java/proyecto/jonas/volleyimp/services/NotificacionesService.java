package proyecto.jonas.volleyimp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import proyecto.jonas.volleyimp.broadcast.BroadcastServiceReceiver;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.utils.Utils;


public class NotificacionesService extends Service {

    private Timer mTimer;
    private HashMap mHmMonedas;
    private Moneda monedaItem;
    private String monedaName;
    private String compraValue;
    private String ventaValue;
    private ArrayList<Moneda> monedaList = new ArrayList<Moneda>();
    private HashMap<String, Moneda> hmMonedas = new HashMap<>();
    private  Bundle bundle = new Bundle();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startTimer() {
        if(mTimer == null){
            mTimer = new Timer();
            mTimer.schedule(timerTask, 2000,7000);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("dolar-app", "onStartCommand "+ monedaList.toString());
        initProperties(intent);
        startTimer();
        return START_STICKY;
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            notifyServiceActions();
        }
    };

    private void toastTest() {
        Log.d("dolar-app","MENSAJE DE PRUEBA");
    }

    private void notifyServiceActions() {
      //  toastTest();
        //TODO: VERIFICAR SI SE ESTA LLAMANDO DESDE BILLETES O COTIZACION AL SERVICIO
        runService();


    }

    private void runService() {
        BilletesService billetesService = new BilletesService(this, new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap hmResponse) {
                toastTest();
                mHmMonedas = hmResponse;
                Moneda monedaNameService = (Moneda) mHmMonedas.get(monedaName);
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });

        billetesService.callRequestData();
    }

    private void initProperties(Intent intent) {
            hmMonedas =(HashMap<String, Moneda>) intent.getSerializableExtra(MonedasConstant.MONEDA_LIST);
    }

    @Override
    public void onDestroy() {
        try{
            Log.d("dolar-app", "Notificacionesservice.class call ondestroy!");
       // si la app no se esta ejecutando en primer plano
           Intent broadcastIntent =  new Intent(getApplicationContext(), BroadcastServiceReceiver.class);
           broadcastIntent.putExtra("holis", "holis");
           broadcastIntent.putExtra(MonedasConstant.MONEDA_LIST, hmMonedas);

           sendBroadcast(broadcastIntent);

           stoptimertask();

        }catch (Exception e){
            Log.d("dolar-app", "Notificacionesservice.class onDestroy error ", e);
            String holis = "";
        }
        super.onDestroy();
    }

    private void stoptimertask() {
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }


}
