package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;

public class DivisasService extends VolleyImp {

    private final static String URL = "http://www.bna.com.ar/Cotizador/MonedasHistorico";

    public DivisasService(Context context, IVolleyCallback _responseEvent) {
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
            // Elements innerTable = Jsoup.parse(htmlString).getElementsByTag("tbody");
            // Elements rows = innerTable.select("tr");

            Document document = Jsoup.parse(htmlString);
            Elements rows = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");

            for(Element row: rows){

                String monedaName = row.getElementsByTag("td").get(0).text();
                String compraValue = row.getElementsByTag("td").get(1).text();
                String ventaValue = row.getElementsByTag("td").get(2).text();

                Moneda moneda = new Moneda();
                moneda.setMonedaName(monedaName);
                moneda.setCompraValue(compraValue);
                moneda.setVentaValue(ventaValue);

                hmCotizacionDivisas.put(monedaName,moneda);
            }

        }catch (Exception e){
            String holis = "";
            //TODO
        }

        return hmCotizacionDivisas;
    }
}
