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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.com.logydes.colegioarji.Adapter.Adapter_Lista_Elementos;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.RestAPI.Adapter.RestAPIAdapter;
import mx.com.logydes.colegioarji.RestAPI.EndPointsAPI;
import mx.com.logydes.colegioarji.RestAPI.Model.TareasResponse;
import mx.com.logydes.colegioarji.Utils.AppConfig;
import mx.com.logydes.colegioarji.Utils.AppController;
import mx.com.logydes.colegioarji.Utils.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;

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

        Log.e(TAG, strURL);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                strURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Utl.hideDialog();
                Log.e("RESPUESTA JSON: ", response );
                try {
                    JSONArray jObj = new JSONArray(response);
                    JSONObject rec = jObj.getJSONObject(0);

                    boolean error = false;

                    String msg = rec.getString("msg");
                    if (!msg.equals("OK")) error = true;

                    if (!error) {
                        ArrayList<Lista_Elementos> MM = new ArrayList<Lista_Elementos>();

                        Integer[] ElementosVarios = new Integer[20];
                        for (int i = 0;  i < ElementosVarios.length; i++ ) {
                            ElementosVarios[i] = 0;
                        }

                        for (int i = 0;  i < jObj.length(); i++ ) {
                            rec = jObj.getJSONObject(i);
                            int idelemento = 0;
                            int idelementodestinatario = 0;
                            int idmobilemensaje = 0;
                            int counter = 0;
                            int status_read = 0;
                            String label = "";
                            String lblProfesor = "";
                            String lblDirector = "";
                            String xml = "";
                            String fecha = "";
                            String mensaje = "";
                            switch (Type){
                                case 0:
                                    idelemento = rec.getInt("idtarea");
                                    idelementodestinatario = rec.getInt("idtareadestinatario");
                                    label = rec.getString("titulo_tarea");
                                    lblProfesor = rec.getString("profesor");;
                                    status_read = rec.getInt("isleida");;
                                    Log.d("Status Read ", String.valueOf(status_read));
                                    MM.add( new Lista_Elementos(label,lblProfesor,idelemento,idelementodestinatario,Type, status_read) );
                                    break;
                                case 1:
                                    idelemento = rec.getInt("idcommensaje");
                                    idelementodestinatario = rec.getInt("idcommensajedestinatario");
                                    label = rec.getString("titulo_mensaje");
                                    lblDirector = "";
                                    status_read = 1;
                                    Log.d("Status Read ", String.valueOf(status_read));
                                    MM.add( new Lista_Elementos(idelemento,idelementodestinatario,label,lblDirector,Type, status_read) );
                                    break;
                                case 2:
                                    label             = rec.getString("pdf");
                                    xml               = rec.getString("xml");
                                    idelemento        = rec.getInt("idfactura");
                                    String Directorio = rec.getString("directorio");
                                    MM.add( new Lista_Elementos(label,xml,Directorio,idelemento,Type) );
                                    break;
                                case 3:
                                    idelemento = rec.getInt("idedocta");
                                    int status_movto = rec.getInt("status_movto");
                                    String mes = rec.getString("mes");
                                    String conepto = rec.getString("concepto");
                                    String FechaPago = rec.getString("fecha_de_pago");
                                    String Vencimiento = rec.getString("vencimiento");
                                    int Vencido = rec.getInt("dias_que_faltan_para_vencer");
                                    int PagosDiv = rec.getInt("is_pagos_diversos");
                                    Integer IdConcepto = rec.getInt("idconcepto");

                                    boolean encontrado = false;

                                    for (int j = 0;  j < ElementosVarios.length; j++ ) {
                                        if( ElementosVarios[j].equals(IdConcepto) ) {
                                            encontrado = true;
                                            break;
                                        }
                                    }

                                    if ( !encontrado && status_movto == 0){
                                        ElementosVarios[counter] = IdConcepto;
                                        counter++;
                                    }else{
                                        IdConcepto = 0;
                                    }


                                    MM.add( new Lista_Elementos(status_movto,Vencido,FechaPago,Vencimiento,conepto,mes,idelemento,Type, PagosDiv, IdConcepto) );
                                    break;

                                case 6:
                                    idelemento = rec.getInt("idmobilemensaje");
                                    idmobilemensaje = rec.getInt("idmobilemensaje");
                                    if ( rec.getString("mensaje").length() > 35 ){
                                        label = rec.getString("mensaje").substring(0,35)+"...";
                                    }else{
                                        label = rec.getString("mensaje");
                                    }
                                    mensaje = rec.getString("mensaje");
                                    status_read = rec.getInt("status_read");
                                    Utilidades ut = new Utilidades();
                                    ut.setFechaFrancesa(rec.getString("fecha"));
                                    fecha = ut.getFechaFrancesa();
                                    MM.add( new Lista_Elementos(idelemento,idmobilemensaje,status_read,fecha,mensaje, label, Type) );
                                    break;

                            }

                        }

                        Singleton.setRsElementos(MM);

                        mad = new Adapter_Lista_Elementos(activity, Menu);  // new AdapterLista_Elementos(MM,activity);
                        listaMM.setAdapter(mad);


                    } else {
                        Toast.makeText(context,
                                "Error:. " +msg, Toast.LENGTH_LONG).show();
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
                        "Data Error:... "+error.getMessage(), Toast.LENGTH_LONG).show();
                Utl.hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                Log.e(TAG,Singleton.getUsername());
                Log.e(TAG,String.valueOf(Singleton.getIdAlu()));
                Log.e(TAG,Singleton.getUsername());
                switch (Type){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        params.put("username", Singleton.getUsername());
                        params.put("sts", "-1");
                        params.put("iduseralu", String.valueOf( Singleton.getIdUserAlu() ) );
                        params.put("tipoconsulta", String.valueOf( Type ) );
                        params.put("fuente", "2" );
                        break;
                    case 6:
                        String token = FirebaseInstanceId.getInstance().getToken();
                        params.put("sts", "-1");
                        params.put("iduser", String.valueOf( Singleton.getIdUser() ) );
                        params.put("device", token );
                        params.put("type", "2" );
                        break;

                }
                Log.e("PARAMS: ",String.valueOf(params));

                return params;

            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    public void resfresh_Lista_Elementos(){
        mad = new Adapter_Lista_Elementos(activity, Menu);  // new AdapterLista_Elementos(MM,activity);
        listaMM.setAdapter(mad);
    }

    public void obtenerDatosRetroFit(String strURL, int _Type) {
        Type = _Type;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", Singleton.getUsername());
        params.put("sts", "-1");
        params.put("iduseralu", String.valueOf( Singleton.getIdUserAlu() ) );
        params.put("tipoconsulta", String.valueOf( Type ) );

        RestAPIAdapter restApiAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsAPI endpointsApi = restApiAdapter.establecerConexionPlatSource(gsonMediaRecent);
        Call<TareasResponse> contactoResponseCall = endpointsApi.getRecentMedia(params);

        Log.w("TODO BIEN HASTA ","AQUI antes del enqueue");

        contactoResponseCall.enqueue(new Callback<TareasResponse>() {
            @Override
            public void onResponse(Call<TareasResponse> call, retrofit2.Response<TareasResponse> response) {
                TareasResponse TareasResponse = response.body();

                Singleton.setRsElementos( TareasResponse.getLista_elementoses() );

                // mostrarContactosRV();
                resfresh_Lista_Elementos();

            }

            @Override
            public void onFailure(Call<TareasResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.getLocalizedMessage());
            }

        });

    }


}
