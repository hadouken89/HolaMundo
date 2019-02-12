package proyecto.jonas.volleyimp.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import proyecto.jonas.volleyimp.constants.MonedasConstant;

public class MonedasConverterUtils {


    public static String convertMonedaValue(String monedaName) {

        Double moneda = parseStringToDouble(monedaName);
        NumberFormat formatter = new DecimalFormat("#0.00");
        monedaName = formatter.format(moneda).replace(".",",");

        return monedaName;
    }

    private static double parseStringToDouble(String monedaName) {
        return Double.parseDouble(monedaName.replaceAll(",","."));
    }

    public static String calculateCurrencySpread(String compraValue, String ventaValue) {
        double venta;
        double compra;
        String spread;
        try {

            return calculateSpread(compraValue, ventaValue);

        }catch (Exception e){
            compraValue = replaceCharCurrencyComma(compraValue);
            ventaValue = replaceCharCurrencyComma(ventaValue);
            return calculateSpread(compraValue, ventaValue);
        }
    }

    private static String replaceCharCurrencyComma(String value){
        return value.replace(",", ".");
    }

    private static String calculateSpread(String compraValue, String ventaValue){
       double compra = Double.parseDouble(compraValue);
       double venta = Double.parseDouble(ventaValue);
       return String.valueOf(compra - venta);
    }

}
