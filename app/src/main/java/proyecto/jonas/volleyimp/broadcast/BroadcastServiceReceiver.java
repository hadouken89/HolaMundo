package proyecto.jonas.volleyimp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;

import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.services.NotificacionesService;

public class BroadcastServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("dolar-app", "BroadcastReceiver OnReceive: Service Stops! and starting again");
        Intent broadCastIntent = new Intent(context, NotificacionesService.class);
        HashMap<String, Moneda> hmMonedas = null;
        hmMonedas = (HashMap<String, Moneda>) intent.getSerializableExtra(MonedasConstant.MONEDA_LIST);
        broadCastIntent.putExtra(MonedasConstant.MONEDA_LIST, hmMonedas);

        context.startService(broadCastIntent);
    }
}
