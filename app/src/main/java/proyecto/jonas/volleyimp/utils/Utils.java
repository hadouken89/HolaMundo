package proyecto.jonas.volleyimp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.constants.MonedasConstant;

public class Utils {

    public static boolean showingDialogMessage = false;
    private static AlertDialog.Builder dlgAlert;

    public static void  alertError(Context context, String message) {
        dlgAlert  = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle("Error")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // ActivityCompat.finishAffinity((Activity) context);
                        showingDialogMessage = false;
                    }
                })
                .setCancelable(true);

        if(!showingDialogMessage){
            showingDialogMessage = true;
            dlgAlert.create().show();
        }
    }

    public static int getMonedaId(String monedaName) {
        switch (monedaName) {
            case MonedasConstant.DOLAR_MONEDA:
                return MonedasConstant.DOLAR_MONEDA_KEY;
            case MonedasConstant.REAL_MONEDA:
                return MonedasConstant.REAL_MONEDA_KEY;
            case MonedasConstant.EURO_MONEDA:
                return MonedasConstant.EURO_MONEDA_KEY;
            case MonedasConstant.FRANCO_SUIZOS_MONEDA:
                return MonedasConstant.FRANCO_SUIZOS_MONEDA_KEY;
            case MonedasConstant.YENES_MONEDA:
                return MonedasConstant.YENES_MONEDA_KEY;
            case MonedasConstant.DOLARES_CANADIENSES_MONEDA:
                return MonedasConstant.DOLARES_CANADIENSES_MONEDA_KEY;
            case MonedasConstant.CORONAS_DANESAS_MONEDA:
                return MonedasConstant.CORONAS_DANESAS_MONEDA_KEY;
            case MonedasConstant.CORONAS_NORUEGAS_MONEDA:
                return MonedasConstant.CORONAS_NORUEGAS_MONEDA_KEY;
            case MonedasConstant.CORONAS_SUECAS_MONEDA:
                return MonedasConstant.CORONAS_SUECAS_MONEDA_KEY;
            case MonedasConstant.LIBRA_ESTERLINA_MONEDA:
                return MonedasConstant.LIBRA_ESTERLINA_MONEDA_KEY;
            default:
                return 0;
        }
    }

    public static int getMonedaImage(int monedaId) {
        switch (monedaId){
            case MonedasConstant.DOLAR_MONEDA_KEY:
                return  R.drawable.dolar_usa;
            case MonedasConstant.CORONAS_DANESAS_MONEDA_KEY:
                return R.drawable.coronas_danesas;
            case MonedasConstant.CORONAS_NORUEGAS_MONEDA_KEY:
                return R.drawable.coronas_noruegas;
            case MonedasConstant.CORONAS_SUECAS_MONEDA_KEY:
                return R.drawable.coronas_suecas;
            case MonedasConstant.DOLARES_CANADIENSES_MONEDA_KEY:
                return  R.drawable.dolares_canadienses;
            case MonedasConstant.EURO_MONEDA_KEY:
                return R.drawable.euro;
            case MonedasConstant.FRANCO_SUIZOS_MONEDA_KEY:
                return R.drawable.franco_suizo;
            case MonedasConstant.LIBRA_ESTERLINA_MONEDA_KEY:
                return R.drawable.libra_esterlina;
            case MonedasConstant.REAL_MONEDA_KEY:
                return  R.drawable.real;
            case MonedasConstant.YENES_MONEDA_KEY:
                return R.drawable.yenes;

            default:
                return 0;

        }
    }

    public static String getMonedaUM(int monedaId) {
        switch (monedaId){
            case MonedasConstant.DOLAR_MONEDA_KEY:
                return  MonedasConstant.DOLAR_UM;
            case MonedasConstant.CORONAS_DANESAS_MONEDA_KEY:
                return MonedasConstant.CORONAS_DANESAS_UM;
            case MonedasConstant.CORONAS_NORUEGAS_MONEDA_KEY:
                return MonedasConstant.CORONAS_NORUEGAS_UM;
            case MonedasConstant.CORONAS_SUECAS_MONEDA_KEY:
                return MonedasConstant.CORONAS_SUECAS_UM;
            case MonedasConstant.DOLARES_CANADIENSES_MONEDA_KEY:
                return  MonedasConstant.DOLARES_CANADIENSES_UM;
            case MonedasConstant.EURO_MONEDA_KEY:
                return MonedasConstant.EURO_UM;
            case MonedasConstant.FRANCO_SUIZOS_MONEDA_KEY:
                return MonedasConstant.FRANCO_SUIZOS_UM;
            case MonedasConstant.LIBRA_ESTERLINA_MONEDA_KEY:
                return MonedasConstant.LIBRA_ESTERLINA_UM;
            case MonedasConstant.REAL_MONEDA_KEY:
                return  MonedasConstant.REAL_UM;
            case MonedasConstant.YENES_MONEDA_KEY:
                return MonedasConstant.YENES_UM;

            default:
                return "";

        }
    }

}
