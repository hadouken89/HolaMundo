package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;

import java.util.HashMap;

import proyecto.jonas.volleyimp.utils.UtilsParseBilletes;

public class BilletesService extends VolleyImp {

    private final static String URL = "http://www.bna.com.ar/Personas";

    public BilletesService(Context context, IVolleyCallback _responseEvent) {
        super(context, _responseEvent);
    }

    public void callRequestData(){
        getJsonRequest();
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    protected HashMap parseResponseData(String jsonString) {
        return getHmMonedas(jsonString);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    private HashMap getHmMonedas(String htmlString){
        UtilsParseBilletes utilParseMoneda = new UtilsParseBilletes(htmlString);

        return utilParseMoneda.getMonedas();
    }
}
