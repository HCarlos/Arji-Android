package mx.com.logydes.colegioarji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

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

public class Elemento extends AppCompatActivity {
    private static final String TAG = "RESPUESTA";

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento);
        // setContentView(R.layout.activity_lista_elementos);
        webview = (WebView) findViewById(R.id.wvEle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        String Elemento = params.getString(getResources().getString(R.string.elemento));
        int IdElemento = params.getInt(getResources().getString(R.string.idelemento));
        int IdElementoDestinatario = params.getInt(getResources().getString(R.string.idelementodestinatario));
        int TipoElemento = params.getInt(getResources().getString(R.string.tipoelemento));

        // DocumentInside bi = new DocumentInside(webview,this);
        // bi.onCreateObject(AppConfig.URL_TAREAS_CIRCULARES, AppConfig.URL_TAREAS_CIRCULARES_TYPE);

        /*
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user",  Singleton.getUsername()  ));
        nameValuePairs.add(new BasicNameValuePair("idtarea", String.valueOf(IdElemento) ));
        nameValuePairs.add(new BasicNameValuePair("idtareadestinatario", String.valueOf(IdElementoDestinatario) ));

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(AppConfig.URL_TAREA);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String data = new BasicResponseHandler().handleResponse(response);
            webview.loadData(data, "text/html", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        String postData = "";
        String url = "";
        switch (TipoElemento){
            case 0:
                postData = "user="+Singleton.getUsername()+"&idtarea="+String.valueOf(IdElemento)+"&idtareadestinatario="+String.valueOf(IdElementoDestinatario);
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
        webview.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));

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
