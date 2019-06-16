package proyecto.jonas.volleyimp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import proyecto.jonas.volleyimp.models.Moneda;

public class UtilsParseBilletes {

    private String htmlString;

    public UtilsParseBilletes(String htmlString) {
        this.htmlString = htmlString;
    }

    public HashMap getMonedas(){
        Elements rows = getRowElements();
        return getMonedasHm(rows);
    }

    private Elements getRowElements(){
        htmlString = getStringTable(htmlString, "billetes");
        Document document = Jsoup.parse(htmlString);
        Elements rows = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        return rows;
    }

    private HashMap getMonedasHm(Elements rows) {
        HashMap hmMonedas = new HashMap();

        try {
            for(Element row: rows){
                Moneda moneda = new Moneda();
                moneda =  setMonedaValues(row, moneda);
                hmMonedas.put(moneda.getMonedaName(),moneda);
            }
        }catch (Exception e){
            //TODO
            String holis = "";
        }

        return hmMonedas;
    }

    private Moneda setMonedaValues(Element row, Moneda moneda) {
        String monedaName = row.getElementsByTag("td").get(0).text().replace("*","").trim();
        String compraValue = row.getElementsByTag("td").get(1).text();
        String ventaValue = row.getElementsByTag("td").get(2).text();

        moneda.setMonedaName(monedaName);
        moneda.setCompraValue(compraValue);
        moneda.setVentaValue(ventaValue);
        moneda.setIdMoneda(Utils.getMonedaId(monedaName));
        moneda.setUnidadMonedaria(Utils.getMonedaUM(moneda.getIdMoneda()));
        return moneda;
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
