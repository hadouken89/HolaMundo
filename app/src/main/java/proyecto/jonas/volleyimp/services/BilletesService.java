package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;

public class BilletesService extends VolleyImp {

    private final static String URL = "http://www.bna.com.ar/Personas"; // "http://www.bna.com.ar/Cotizador/MonedasHistorico"; //"https://www.infodolar.com/";//

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
       // return convertHashmapToJsonString(responseToObject(jsonString));
    }

    @Override
    public String getUrl() {
        return URL;
    }

    private HashMap getHmMonedas(String htmlString){
        HashMap hmMonedas = new HashMap();
        try {
            htmlString = getStringTable(htmlString, "billetes");
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

                hmMonedas.put(monedaName,moneda);
            }

        }catch (Exception e){
            //TODO
            String holis = "";
        }

        return hmMonedas;
    }
    private String getStringTable(String htmlString,String idParam) {
        String idBilletes = "id=\"" + idParam + "\">";
        int indexTableBilletesBegan = htmlString.indexOf(idBilletes) + idBilletes.length();
        htmlString.substring(htmlString.indexOf(idBilletes));

        String idTable = "</table>";
        int indexTableBilletesEnd = htmlString.indexOf(idTable) + idTable.length();
        htmlString =  htmlString.substring(indexTableBilletesBegan, indexTableBilletesEnd);

        return htmlString;
    }


}
