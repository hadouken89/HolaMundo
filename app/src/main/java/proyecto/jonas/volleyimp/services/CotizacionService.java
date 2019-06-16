package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;

import java.util.HashMap;

import proyecto.jonas.volleyimp.utils.UtilsParseCotizacion;

public class CotizacionService extends VolleyImp {

    private final static String URL = "http://www.bna.com.ar/Cotizador/MonedasHistorico";

    public CotizacionService(Context context, IVolleyCallback _responseEvent) {
        super(context, _responseEvent);
    }

    public void callRequestData(){
        getJsonRequest();
    }

    @Override
    protected String getUrl() {
        return URL;
    }

    @Override
    protected int getMethod() {
        return Request.Method.POST;
    }

    @Override
    protected HashMap parseResponseData(String htmlString) {
        return getHmCotizacion(htmlString);
    }

    private HashMap getHmCotizacion(String htmlString) {
        UtilsParseCotizacion utilsParseCotizacion = new UtilsParseCotizacion(htmlString);
        return utilsParseCotizacion.getCotizacion();
    }
}
