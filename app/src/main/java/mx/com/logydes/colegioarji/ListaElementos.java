package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mx.com.logydes.colegioarji.DB.dbLista_Elementos;
import mx.com.logydes.colegioarji.Utils.AppConfig;

public class ListaElementos extends AppCompatActivity {
    private int IdMenu;
    private String Menu;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);

        // getSupportActionBar().hide();//Ocultar ActivityBar anterio
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar2);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            final Drawable leftArrow = getResources().getDrawable(R.drawable.ic_arrow_left);
            leftArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(leftArrow);

        }

        webview = (WebView) findViewById(R.id.webview);

        Bundle params = getIntent().getExtras();
        Menu = params.getString(getResources().getString(R.string.menu));
        IdMenu = params.getInt(getResources().getString(R.string.idmenu));

        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(Menu);

        ImageView img = (ImageView) findViewById(R.id.btnRefresh);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatos();
            }
        });

        getDatos();


/*
        srlRefresh = (SwipeRefreshLayout) findViewById(R.id.srlRefresh);
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlRefresh.setRefreshing(false);
            }
        });
*/
    }

    public void getDatos(){


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
            case 6:
                strURL = AppConfig.URL_NOTIFICACIONES;
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
