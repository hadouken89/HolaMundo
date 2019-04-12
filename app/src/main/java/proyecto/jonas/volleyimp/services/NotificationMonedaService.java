package proyecto.jonas.volleyimp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.HashMap;

import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;

public class NotificationMonedaService extends Service {

    private HashMap mHmMonedas;
    private Moneda monedaItem;
    private String monedaName;
    private String compraValue;
    private String ventaValue;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initProperties(intent);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runService();
            }
        },20000);

        return START_STICKY;
    }

    private void runService() {
        BilletesService billetesService = new BilletesService(this, new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap hmResponse) {
                mHmMonedas = hmResponse;
                Moneda monedaNameService = (Moneda) mHmMonedas.get(monedaName);
                checkMonedaCotizacion(monedaNameService);
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });

        billetesService.callRequestData();
    }

    private void initProperties(Intent intent) {
        monedaItem = (Moneda) intent.getExtras().get(MonedasConstant.ITEM_MONEDA);
        monedaName = monedaItem.getMonedaName();
        compraValue = monedaItem.getCompraValue();
        ventaValue = monedaItem.getVentaValue();
    }

    private void checkMonedaCotizacion(Moneda monedaNameService) {
        Toast.makeText(this,"estoy llamando al service", Toast.LENGTH_LONG);
     //   double compraValueService = Double.parseDouble(monedaNameService.getCompraValue().replace(",","."));
     //   double ventaValueService = Double.parseDouble(monedaNameService.getVentaValue().replace(",","."));
     //   checkCotizacionEstado(Double.parseDouble(compraValue.replace(",",".")), compraValueService );
     //   checkCotizacionEstado(Double.parseDouble(ventaValue.replace(",",".")), ventaValueService);
    }

    private void checkCotizacionEstado(double valorActual, double valorNuevo) {
        if(  valorActual <  valorNuevo){
            // TODO: subio la moneda seleccionada, enviar notificaciones
            String holis = "";
        }

        if( valorActual  >  valorNuevo){
            // TODO: bajo la moneda seleccionada, enviar notificaciones
            String holis = "";
        }
    }
}
