package mx.com.logydes.colegioarji.Utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by devch on 17/06/16.
 */
public class Utilidades {

    private static final String TAG = "RESPUESTA";
    private ProgressDialog pDialog;
    private String FechaFrancesa;

    public Utilidades() {}

    public Utilidades(ProgressDialog pDialog) {
        this.pDialog = pDialog;
    }

    public void showDialog() {
        if (!pDialog.isShowing())
            this.pDialog.show();
    }

    public void hideDialog() {
        if (pDialog.isShowing())
            this.pDialog.hide();
    }

    public String getFechaFrancesa() {
        String ff = "";
        String aff[] = FechaFrancesa.split(" ");
        String fff[] = aff[0].split("-");
        String ffff = fff[2]+"-"+fff[1]+"-"+fff[0];
        ff = ffff+" "+aff[1];
        return ff;
    }

    public void setFechaFrancesa(String fechaFrancesa) {
        FechaFrancesa = fechaFrancesa;
    }

    public String FechaFrancesa(String fecha){
        String ff = "";
        String aff[] = fecha.split(" ");
        ff = aff[1]+" "+aff[1];
        return ff;
    }
}
