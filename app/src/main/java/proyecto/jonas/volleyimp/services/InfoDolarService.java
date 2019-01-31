package proyecto.jonas.volleyimp.services;

import android.content.Context;

import com.android.volley.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;

public class InfoDolarService extends VolleyImp {

    private static final String INFODOLAR_URL = "https://www.infodolar.com";

    public InfoDolarService(Context context, IVolleyCallback _responseEvent) {
        super(context, _responseEvent);
    }

    public void callRequestData(){
        getJsonRequest();
    }

    @Override
    protected String getUrl() {
        return INFODOLAR_URL;
    }

    @Override
    protected int getMethod() {
        return Request.Method.GET;
    }

    @Override
    protected HashMap parseResponseData(String jsonString) {
        HashMap hmCotizacionDivisas = new HashMap();

        try{
            // Elements innerTable = Jsoup.parse(htmlString).getElementsByTag("tbody");
            // Elements rows = innerTable.select("tr");
            getStringTable(jsonString,"ctl00_PlaceHolderMainContent_GridViewDolar");
            Document document = Jsoup.parse(jsonString);
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
