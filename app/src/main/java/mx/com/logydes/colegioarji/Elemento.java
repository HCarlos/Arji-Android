package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import mx.com.logydes.colegioarji.DB.dbLista_Elementos;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Inside.DocumentInside;
import mx.com.logydes.colegioarji.Utils.AppConfig;
import mx.com.logydes.colegioarji.Utils.Utilidades;

public class Elemento extends AppCompatActivity {
    private static final String TAG = "RESPUESTA";
    private ProgressDialog pDialog;
    private Utilidades Utl;
    private String Elemento;
    private int IdElemento;
    private int IdElementoDestinatario;
    private int TipoElemento;
    private String Menu;
    private String PDF;
    private String Directorio;
    private String Concepto;
    private String Mes;
    private String FechaPago;
    private int Status_Movto;
    private int Vencido;

    private WebView webview;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento);
        // setContentView(R.layout.activity_lista_elementos);
        this.context = this;
        this.webview = (WebView) findViewById(R.id.wvEle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        Elemento = params.getString(getResources().getString(R.string.elemento));
        IdElemento = params.getInt(getResources().getString(R.string.idelemento));
        IdElementoDestinatario = params.getInt(getResources().getString(R.string.idelementodestinatario));
        TipoElemento = params.getInt(getResources().getString(R.string.tipoelemento));
        Menu = params.getString(getResources().getString(R.string.menuelemento));
        PDF = params.getString(getResources().getString(R.string.PDF));
        Directorio = params.getString(getResources().getString(R.string.Directorio));

        Mes = params.getString(getResources().getString(R.string.Mes));
        Concepto = params.getString(getResources().getString(R.string.Concepto));
        FechaPago = params.getString(getResources().getString(R.string.FechaPago));
        Status_Movto = params.getInt(getResources().getString(R.string.Status_Movto));
        Vencido = params.getInt(getResources().getString(R.string.Vencido));

        //Log.e(TAG,Menu);
        this.setTitle(Menu);

        getDocuments();

    }

    private boolean getDocuments(){

        String postData = "";
        String url = "";
        switch (TipoElemento){
            case 0:
                postData = "user="+Singleton.getUsername()+
                        "&idtarea="+String.valueOf(IdElemento)+
                        "&idtareadestinatario="+String.valueOf(IdElementoDestinatario);
                url = AppConfig.URL_TAREA;
                break;
            case 1:
                postData = "user="+Singleton.getUsername()+
                        "&idcommensaje="+String.valueOf(IdElemento)+
                        "&idcommensajedestinatario="+String.valueOf(IdElementoDestinatario)+
                        "&sts=0";
                url = AppConfig.URL_CIRCULARES;
                break;
            case 2:
                url = "http://docs.google.com/gview?embedded=true&url=" + "http://platsource.mx/uw_fe/"+Directorio+PDF;
                break;

            case 3:
                postData = "user="+Singleton.getUsername()+
                        "&iduser="+String.valueOf( Singleton.getIdUser()  )+
                        "&idedocta="+String.valueOf(IdElemento);
                url = AppConfig.URL_VIEW_PAGOS;
                break;
        }

        Log.e(TAG,url+postData);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            this.webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.setWebViewClient(new Callback());
        pDialog = new ProgressDialog(this.context);
        pDialog.setCancelable(false);
        String postGet = "";
        switch (TipoElemento) {
            case 0:
            case 1:
            case 3:
                this.webview.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
                break;
            case 2:
                this.webview.loadUrl(url);
                break;
        }
        this.webview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lpView);
                pDialog.setMessage("Cargando..."+Elemento);
                Utl = new Utilidades(pDialog);
                Utl.showDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pDialog.setMessage("...");
                Utl = new Utilidades(pDialog);
                Utl.hideDialog();
            }

        });

        return true;
    }

    private class Callback extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
