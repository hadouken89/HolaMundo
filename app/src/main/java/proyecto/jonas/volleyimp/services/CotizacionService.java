package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.utils.Utils;
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
        HashMap hmCotizacionDivisas = new HashMap();

        try{
            UtilsParseCotizacion utilsParseCotizacion = new UtilsParseCotizacion(htmlString);
            hmCotizacionDivisas = utilsParseCotizacion.getCotizacionList();

        }catch (Exception e){
            String holis = "";
            //TODO
        }

        return hmCotizacionDivisas;
    }
}
