package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
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


    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(urlBoleta);

        try {
            // Add your data
            String nc = "u=" + Username + "&strgrualu=" + IdGruAlu + "&logoEmp=" + logoEmp + "&logoIbo=" + logoIB;

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("o", "0"));
            nameValuePairs.add(new BasicNameValuePair("t", "40"));
            nameValuePairs.add(new BasicNameValuePair("c", nc));
            nameValuePairs.add(new BasicNameValuePair("from", "0"));
            nameValuePairs.add(new BasicNameValuePair("cantidad", "0"));
            nameValuePairs.add(new BasicNameValuePair("s", " order by orden_impresion asc "));
/*
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            this.webview.loadData(response.toString(),"application/pdf","utf8");
            setContentView(this.webview);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
*/

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(urlBoleta);
            HttpResponse response = httpclient.execute(httppost);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Respnce body :: " + responseBody);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            String LineSeparator = System.getProperty("line.separator");

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + LineSeparator);
            }
            bufferedReader.close();

            //Toast.makeText(View_Materials_Activity.this, "Finished", Toast.LENGTH_LONG).show();

            String webData = stringBuffer.toString();
            String webData1 = URLDecoder.decode(webData);
            System.out.println("data :; " + webData1);
            this.webview.loadData(webData, "text/html", "UTF-8");
            this.webview.loadDataWithBaseURL(urlBoleta, webData, "application/pdf", "UTF-8", "about:blank");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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




    private boolean getDocuments(String urlBoleta, String _User, int _strGrualu, String _logoEmp, String _logoIB){

        String postData = "user="+Singleton.getUsername()+
                "&iduser="+Singleton.getIdUser()+
                "&idgrualu="+Singleton.getIdUserAlu()+
                "&idemp="+Singleton.getIdEmp();

        String nc = "&u="+_User+"&strgrualu="+_strGrualu+"&logoEmp="+_logoEmp+"&logoIbo="+_logoIB;
        String s = " order by orden_impresion asc ";
        // postData = "o=0&t=40&c=0&from=0&cantidad=0&s="+s+nc;

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
        // this.webview.setWebViewClient(new WebViewClient());

        // this.webview.setWebViewClient(new Callback());
        pDialog = new ProgressDialog(this.context);
        pDialog.setCancelable(false);
        webview.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
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
