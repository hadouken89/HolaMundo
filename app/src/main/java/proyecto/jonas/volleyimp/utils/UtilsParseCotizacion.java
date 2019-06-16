package proyecto.jonas.volleyimp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;

public class UtilsParseCotizacion {


    private final String htmlString;

    public UtilsParseCotizacion(String htmlString ) {
        this.htmlString = htmlString;
    }

    public HashMap getCotizacionList(){
        Elements rows = getRowElements();
        return getCotizacionHm(rows);
    }

    private HashMap getCotizacionHm(Elements rows) {
        HashMap hmCotizacionDivisas = new HashMap();
        try{
            for(Element row: rows){
                Moneda moneda = setCotizacionParams(row);
                hmCotizacionDivisas.put(moneda.getMonedaName(),moneda);
            }

        }catch (Exception e){
            String holis = "";
            //TODO
        }

        return hmCotizacionDivisas;
    }

    private Moneda setCotizacionParams(Element row) {
        Moneda moneda = new Moneda();
        String monedaName = row.getElementsByTag("td").get(0).text().replace("(*)","").trim();
        String compraValue = row.getElementsByTag("td").get(1).text();
        String ventaValue = row.getElementsByTag("td").get(2).text();

        moneda.setMonedaName(monedaName);
        moneda.setCompraValue(compraValue);
        moneda.setVentaValue(ventaValue);
        moneda.setIdMoneda(Utils.getMonedaId(monedaName));
        moneda.setUnidadMonedaria(Utils.getMonedaUM(moneda.getIdMoneda()));

        return moneda;
    }

    private Elements getRowElements() {
        Document document = Jsoup.parse(htmlString);
        Elements rows = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        return  rows;
    }
}
