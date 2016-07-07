package mx.com.logydes.colegioarji.DB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Path;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.com.logydes.colegioarji.Adapter.Adapter_Lista_Elementos;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.Utils.AppController;
import mx.com.logydes.colegioarji.Utils.Utilidades;

/**
 * Created by devch on 24/06/16.
 */

public class dbLista_Elementos {
    private static final String TAG = "RESPUESTA";
    private ProgressDialog pDialog;
    private Utilidades Utl;
    private Context context;
    private ArrayList<Lista_Elementos> mm;
    private RecyclerView listaMM;
    private Activity activity;
    private Adapter_Lista_Elementos mad;
    private int Type;
    private String Menu;

    public dbLista_Elementos(Context context, Activity act, String _menu) {
        this.context = context;
        this.activity = act;
        this.Menu = _menu;
        listaMM = (RecyclerView) act.findViewById(R.id.rvListaElementos);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMM.setLayoutManager(llm);
    }

    public void obtenerDatos(String strURL, int _Type){

        Type = _Type;

        String tag_string_req = "req_login";

        pDialog = new ProgressDialog(activity);
        pDialog.setCancelable(false);
        pDialog.setMessage("Cargando...");
        Utl = new Utilidades(pDialog);
        Utl.showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                strURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Utl.hideDialog();
                Log.e(TAG, response );
                try {
                    JSONArray jObj = new JSONArray(response);
                    JSONObject rec = jObj.getJSONObject(0);

                    boolean error = false;

                    String msg = rec.getString("msg");
                    if (!msg.equals("OK")) error = true;

                    if (!error) {
                        ArrayList<Lista_Elementos> MM = new ArrayList<Lista_Elementos>();
                        for (int i = 0;  i < jObj.length(); i++ ) {
                            rec = jObj.getJSONObject(i);
                            int idelemento = 0;
                            int idelementodestinatario = 0;
                            String label = "";
                            switch (Type){
                                case 0:
                                    idelemento = rec.getInt("idtarea");
                                    idelementodestinatario = rec.getInt("idtareadestinatario");
                                    label = rec.getString("titulo_tarea");
                                    MM.add( new Lista_Elementos(label,idelemento,idelementodestinatario,Type) );
                                    break;
                                case 1:
                                    idelemento = rec.getInt("idcommensaje");
                                    idelementodestinatario = rec.getInt("idcommensajedestinatario");
                                    label = rec.getString("titulo_mensaje");
                                    MM.add( new Lista_Elementos(idelemento,idelementodestinatario,label,Type) );
                                    break;
                                case 2:
                                    label = rec.getString("pdf");
                                    idelemento = rec.getInt("idfactura");
                                    String Directorio = rec.getString("directorio");
                                    MM.add( new Lista_Elementos(label,Directorio,idelemento,Type) );
                                    break;
                                case 3:
                                    idelemento = rec.getInt("idedocta");
                                    int status_movto = rec.getInt("status_movto");
                                    String mes = rec.getString("mes");
                                    String conepto = rec.getString("concepto");
                                    String FechaPago = rec.getString("fecha_de_pago");
                                    int Vencido = rec.getInt("dias_que_faltan_para_vencer");
                                    MM.add( new Lista_Elementos(status_movto,Vencido,FechaPago,conepto,mes,idelemento,Type) );
                                    break;
                            }

                        }

                        Singleton.setRsElementos(MM);

                        mad = new Adapter_Lista_Elementos(activity, Menu);  // new AdapterLista_Elementos(MM,activity);
                        listaMM.setAdapter(mad);


                    } else {
                        Toast.makeText(context,
                                msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Data Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Utl.hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                /// Log.e(TAG,String.valueOf(Type));
                Log.e(TAG,String.valueOf(Singleton.getIdAlu()));
                Log.e(TAG,Singleton.getUsername());
                switch (Type){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        params.put("username", Singleton.getUsername());
                        params.put("sts", "0");
                        params.put("iduseralu", String.valueOf( Singleton.getIdAlu() ) );
                        params.put("tipoconsulta", String.valueOf( Type ) );
                        break;
                }
                /*
                if (Type == 0){
                    params.put("username", Singleton.getUsername());
                    params.put("sts", "0");
                    params.put("iduseralu", String.valueOf( Singleton.getIdAlu() ) );
                    params.put("tipoconsulta", String.valueOf( Type ) );
                }
                if (Type == 1){
                    params.put("username", Singleton.getUsername());
                    params.put("sts", "0");
                    params.put("iduseralu", String.valueOf( Singleton.getIdAlu() ) );
                    params.put("tipoconsulta", String.valueOf( Type ) );
                }
                if (Type == 2){
                    params.put("username", Singleton.getUsername());
                    params.put("sts", "0");
                    params.put("iduseralu", String.valueOf( Singleton.getIdAlu() ) );
                    params.put("tipoconsulta", String.valueOf( Type ) );
                }
                */
                return params;

            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    public void resfresh_Lista_Elementos(){
        mad = new Adapter_Lista_Elementos(activity, Menu);  // new AdapterLista_Elementos(MM,activity);
        listaMM.setAdapter(mad);
    }

}
