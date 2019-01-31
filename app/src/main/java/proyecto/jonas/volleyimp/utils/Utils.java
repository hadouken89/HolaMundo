package proyecto.jonas.volleyimp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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


}
