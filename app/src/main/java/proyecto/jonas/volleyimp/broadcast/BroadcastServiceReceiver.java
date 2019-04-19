package proyecto.jonas.volleyimp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.services.NotificacionesService;

public class BroadcastServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("dolar-app", "BroadcastReceiver OnReceive: Service Stops! and starting again");
        Intent broadCastIntent = new Intent(context, NotificacionesService.class);
        Moneda monedaItem = (Moneda) intent.getExtras().get(MonedasConstant.ITEM_MONEDA);
        broadCastIntent.putExtra(MonedasConstant.ITEM_MONEDA, monedaItem );

        context.startService(broadCastIntent);

    }
}
