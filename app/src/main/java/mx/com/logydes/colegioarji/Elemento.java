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

    private WebView webview;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento);
        // setContentView(R.layout.activity_lista_elementos);
        context = this;
        webview = (WebView) findViewById(R.id.wvEle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        Elemento = params.getString(getResources().getString(R.string.elemento));
        int IdElemento = params.getInt(getResources().getString(R.string.idelemento));
        int IdElementoDestinatario = params.getInt(getResources().getString(R.string.idelementodestinatario));
        int TipoElemento = params.getInt(getResources().getString(R.string.tipoelemento));

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
        }
        Log.e(TAG,postData);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.getSettings().setJavaScriptEnabled(true);

        this.pDialog = new ProgressDialog(this.context);
        this.pDialog.setCancelable(false);
        webview.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
        webview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
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
