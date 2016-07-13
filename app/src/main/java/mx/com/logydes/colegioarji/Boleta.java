package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Utils.AppConfig;
import mx.com.logydes.colegioarji.Utils.Utilidades;

public class Boleta extends AppCompatActivity {
    private static final String TAG = "RESPUESTA";
    private ProgressDialog pDialog;
    private Utilidades Utl;
    private String Username;
    private int IdUser;
    private int IdUserAlu;
    private int IdGruAlu;
    private int IdEmp;
    private String urlBoleta;
    private String logoEmp;
    private String logoIB;
    private int IsBoleta;
    private String postData;

    private WebView webview;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento);
        this.context = this;
        this.webview = (WebView) findViewById(R.id.wvEle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        Username = params.getString(getResources().getString(R.string.Username));
        IdUser = params.getInt(getResources().getString(R.string.IdUser));
        IdUserAlu = params.getInt(getResources().getString(R.string.IdUserAlu));
        IdGruAlu = params.getInt(getResources().getString(R.string.IdGruAlu));
        IdEmp = params.getInt(getResources().getString(R.string.IdEmp));
        urlBoleta =params.getString(getResources().getString(R.string.urlBoleta));
        logoEmp = params.getString(getResources().getString(R.string.logoEmp));
        logoIB = params.getString(getResources().getString(R.string.logoIB));
        IsBoleta = params.getInt(getResources().getString(R.string.IsBoleta));

        this.setTitle("Boleta");

        // postData();
        // getDocuments2(urlBoleta, Username, IdGruAlu, logoEmp, logoIB);
        getDocuments(urlBoleta, Username, IdGruAlu, logoEmp, logoIB);

    }

/*
    private boolean getDocuments2(String urlBoleta, String _User, int _strGrualu, String _logoEmp, String _logoIB){

        // urlBoleta = "http://docs.google.com/gview?embedded=true&url=" + urlBoleta;

        String nc = "&u="+_User+"&strgrualu="+_strGrualu+"&logoEmp="+_logoEmp+"&logoIbo="+_logoIB;
        String s = " order by orden_impresion asc ";
        postData = "o=0&t=40&c=0&from=0&cantidad=0&s="+s+nc;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            this.webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setSaveFormData(true);
        // this.webview.setWebViewClient(new Callback());
        pDialog = new ProgressDialog(this.context);
        pDialog.setCancelable(false);
        this.webview.postUrl(urlBoleta, EncodingUtils.getBytes(postData, "BASE64"));

        this.webview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lpView);
                pDialog.setMessage("Cargando...");
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

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                // view.postUrl(url, postData.getBytes());
                Log.e("E N T R O : ","SIIIIII");
                return true;
            }

            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed() ;
                Log.e("E R R O R : ",error.toString());
            }

        });


        return true;
    }

*/


    private boolean getDocuments(String urlBoleta, String _User, int _strGrualu, String _logoEmp, String _logoIB){

        /*
        String postData = "user="+Singleton.getUsername()+
                "&iduser="+Singleton.getIdUser()+
                "&idgrualu="+Singleton.getIdUserAlu()+
                "&idemp="+Singleton.getIdEmp();
        */

        String nc = "&u="+_User+"&strgrualu="+_strGrualu+"&logoEmp="+_logoEmp+"&logoIbo="+_logoIB;
        String s = " order by orden_impresion asc ";
        postData = "o=0&t=40&c=0&from=0&cantidad=0&s="+s+nc;

        Log.e(TAG,postData);

        // urlBoleta = "http://docs.google.com/gview?embedded=true&url=" + urlBoleta+"?"+postData;

        String url = AppConfig.URL_VIEW_BOLETAS;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = this.webview.getSettings();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSaveFormData(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebChromeClient(new WebChromeClient());
        // webview.setWebViewClient(new WebViewClient());

        webview.setWebViewClient(new Callback());
        pDialog = new ProgressDialog(this.context);
        pDialog.setCancelable(false);
        webview.postUrl(urlBoleta, EncodingUtils.getBytes(postData, "BASE64"));

        // setContentView(webview);

        // this.webview.loadUrl(url);
        webview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lpView);
                pDialog.setMessage("Cargando...");
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

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return false;
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
            view.postUrl(url, postData.getBytes());
            Log.e("ENTRO", "LO que hace es agarrar macho...");

            return false;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview!=null && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
