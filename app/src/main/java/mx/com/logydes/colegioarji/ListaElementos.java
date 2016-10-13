package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import mx.com.logydes.colegioarji.DB.dbHijos;
import mx.com.logydes.colegioarji.DB.dbLista_Elementos;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Inside.DocumentInside;
import mx.com.logydes.colegioarji.Utils.AppConfig;

public class ListaElementos extends AppCompatActivity {
    private int IdMenu;
    private String Menu;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        webview = (WebView) findViewById(R.id.webview);

        Menu = params.getString(getResources().getString(R.string.menu));
        IdMenu = params.getInt(getResources().getString(R.string.idmenu));
        this.setTitle(Menu);

        dbLista_Elementos cm = new dbLista_Elementos(this, this, Menu);

        String strURL = "";
        switch (IdMenu){
            case 0:
            case 1:
            case 2:
            case 3:
                strURL = AppConfig.URL_TAREAS_CIRCULARES;
                cm.obtenerDatos(strURL,IdMenu);
                // cm.obtenerDatosRetroFit(strURL,IdMenu);
                break;

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
