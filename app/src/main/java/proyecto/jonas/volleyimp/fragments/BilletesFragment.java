package proyecto.jonas.volleyimp.fragments;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.activities.MonedaNotification;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.broadcast.BroadcastServiceReceiver;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.services.BilletesService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;
import proyecto.jonas.volleyimp.services.NotificacionesService;

public class BilletesFragment extends Fragment {

    private ListView lvBilletes;
    private View mView;
    private Intent mIntent ;
    private HashMap mHmMonedas;
    private HashMap hmMonedasInQueue;
    private HashMap<String, Moneda> hmMonedas = new HashMap<>();

    public BilletesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_billetes, container, false);

        initProperties(view);
        return view;
    }

    private void initProperties(View view) {
        lvBilletes = view.findViewById(R.id.lvBilletes);
        hmMonedasInQueue = NotificacionesService.getHmMonedas();

        this.mView = view;
        showDivisas();
    }

  // private void showInfoDolar(){
  //     InfoDolarService infoDolarService = new InfoDolarService(mView.getContext(), new IVolleyCallback() {
  //         @Override
  //         public void onSuccess(HashMap hmResponse) {
  //             String holis = "";
  //         }

  //         @Override
  //         public void onFailure(Exception e) {
  //             String holis = "";
  //         }
  //     });

  //     infoDolarService.callRequestData();
  // }

   private void showDivisas() {
       BilletesService billetesService = new BilletesService(mView.getContext(), new IVolleyCallback() {
           @Override
           public void onSuccess(HashMap hmResponse) {
               mHmMonedas = hmResponse;
               setMonedaNotificationListener();
               showListViewParams(hmResponse);
           }

           @Override
           public void onFailure(Exception e) {
               Log.d("dolar-app", "BilletesFragment showDivisas OnFailure calling billetesService.class");
               e.printStackTrace();
           }
       });
       billetesService.callRequestData();
   }

   private void showListViewParams(HashMap hmParams) {
       MonedasAdapter monedasAdapter = new MonedasAdapter(mView.getContext(),hmParams);
       monedasAdapter.setmOnItemClickListener(new MonedasAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(Moneda moneda) {
              if(! isMonedaInNotificationQueue(moneda)){
                addMonedaToNotificationList(moneda);
                addMonedaToQueue(moneda);
              }

           }
       });
       lvBilletes.setAdapter(monedasAdapter);

   }

    private void addMonedaToQueue(Moneda moneda) {
        if(!isMyServiceRunning(NotificacionesService.class)){
            Log.d("dolar-app", "addMonedaToNotificationList: "+ moneda.getMonedaName() +" - " + moneda.toString() );
            callBroadCastService();
        }else {
            NotificacionesService.addMonedaToHmQueue(moneda);
        }
    }

    private void addMonedaToNotificationList(Moneda moneda) {
        hmMonedasInQueue.put(moneda.getMonedaName(), moneda);
    }

    private void callBroadCastService() {
        Intent broadcastIntent =  new Intent(getContext(), BroadcastServiceReceiver.class);
        broadcastIntent.putExtra(MonedasConstant.MONEDA_LIST, hmMonedasInQueue);

        getContext().sendBroadcast(broadcastIntent);
    }

    private boolean isMonedaInNotificationQueue(Moneda moneda) {
        HashMap hmMonedasInQueue = NotificacionesService.getHmMonedas();

        if(hmMonedasInQueue != null) {
            for (int i = 0; i < hmMonedasInQueue.size(); i++) {
                Moneda monedaInQueue = (Moneda) hmMonedasInQueue.get(hmMonedasInQueue.keySet().toArray()[i]);
                String monedaInQueueName = monedaInQueue.getMonedaName();
                if (moneda.getMonedaName().equals(monedaInQueueName)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.d ("dolar-app", true+" isMyServiceRunning?");
                return true;
            }
        }
        Log.d ("dolar-app",false + " isMyServiceRunning?");
        return false;
    }

   private void setMonedaNotificationListener(){
       lvBilletes.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Moneda monedaItem = getMonedaItem(mHmMonedas, position);
               callMonedaNotification(monedaItem);
           }
       });
   }

    private void callMonedaNotification(Moneda monedaItem) {
        Intent myIntent = new Intent(getActivity(), MonedaNotification.class);
        myIntent.putExtra(MonedasConstant.ITEM_MONEDA, monedaItem);
        startActivity(myIntent);
    }

    private Moneda getMonedaItem(HashMap mHmMonedas, int position) {
        return (Moneda) mHmMonedas.get(mHmMonedas.keySet().toArray()[position]);
    }

    private void stopNotificationService() {
        Intent myIntent = new Intent(getContext(), NotificacionesService.class);
        myIntent.putExtra(MonedasConstant.MONEDA_LIST, hmMonedas);
        getContext().stopService(myIntent);
    }

    @Override
    public void onDestroy() {
        stopNotificationService();
        Log.d("dolar-app", "BilletesFragment call OnDestroy and stop NotificacionesService.class");
        super.onDestroy();
    }
}
