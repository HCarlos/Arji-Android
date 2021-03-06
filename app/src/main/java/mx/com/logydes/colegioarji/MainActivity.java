package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.iid.FirebaseInstanceId;

import mx.com.logydes.colegioarji.Adapter.Adapter_Menu_Tutores;
import mx.com.logydes.colegioarji.DB.dbHijos;
import mx.com.logydes.colegioarji.Helper.SQLiteHandler;
import mx.com.logydes.colegioarji.Helper.SessionManager;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Inside.DocumentInside;
import mx.com.logydes.colegioarji.Pojos.Sistema_Info;
import mx.com.logydes.colegioarji.Utils.AppConfig;

import static android.R.id.message;
import static mx.com.logydes.colegioarji.R.attr.icon;
import static mx.com.logydes.colegioarji.R.attr.title;
import static mx.com.logydes.colegioarji.R.styleable.View;
import static mx.com.logydes.colegioarji.Utils.AppConfig.INDICE_CIRCULAR;
import static mx.com.logydes.colegioarji.Utils.AppConfig.INDICE_FACTURAS;
import static mx.com.logydes.colegioarji.Utils.AppConfig.INDICE_NOTIFICACIONES;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BLUE";
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SQLiteHandler db;
    private SessionManager session;
    private WebView webview;

    private GoogleApiClient client;
    private Singleton singleton;
    private TextView tvBienvenida;
    private LinearLayout llMensajes;
    private RecyclerView listaMM;
    private Sistema_Info sinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleton = new Singleton();
        sinfo = new Sistema_Info("","",0);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String refreshedTokenID = FirebaseInstanceId.getInstance().getId();

        Log.i(TAG, "TOKEN ::> " + refreshedToken);
        Log.w(TAG, "ID TOKEN ::> " + refreshedTokenID);

        sinfo.setToken(refreshedToken);
        sinfo.setToken_ID(refreshedTokenID);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        tvBienvenida = (TextView) findViewById(R.id.txtBienvenida);
        llMensajes = (LinearLayout) findViewById(R.id.llMensajes);
        if (session.isLoggedIn() && singleton.getRsHijosSize() <= 0) {
            db.getUserDetails();
            String nc = singleton.getNombreCompletoUsuario() ;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webview = (WebView) findViewById(R.id.webview);
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (!session.isLoggedIn()){
            if ( tvBienvenida != null ) {
                tvBienvenida.setVisibility(tvBienvenida.INVISIBLE);
                llMensajes.setVisibility(llMensajes.INVISIBLE);
                // dl.setBackgroundResource(R.color.colorBackground);
                dl.setBackgroundResource(R.drawable.background_2_arji);
            }
        }else{
            if ( tvBienvenida != null ) {
                tvBienvenida.setVisibility(tvBienvenida.VISIBLE);
                llMensajes.setVisibility(llMensajes.VISIBLE);
                dl.setBackgroundResource(R.drawable.background_1_arji);
            }
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // Esto cambio apartir de las nuevas versiones
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


/*
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.icon_notification_transparent,
                "message", System.currentTimeMillis());
        // Hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 0, 100, 600, 100, 700};
        vibrator.vibrate(pattern, -1);
        Intent intent = new Intent(this, ListaElementos.class);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        String sms = getSharedPreferences("SMSPREF", MODE_PRIVATE).getString("incoming", "EMPTY");
//        notification.setLatestEventInfo(this, "message" ,
//                sms, activity);
        notification.number += 1;
        notificationManager.notify(0, notification);
*/


        // Intent i = new Intent(this, RegistrationService.class);
        // startService(i);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if ( listaMM != null ) {
            callAdapter(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.e("getIdUserNivelAcceso:", String.valueOf(singleton.getIdUserNivelAcceso() ) );

        sinfo.setIdUser( singleton.getIdUser() );

        if ( session.isLoggedIn() && (
                                        singleton.getIdUserNivelAcceso() == 7 ||
                                        singleton.getIdUserNivelAcceso() == 25
                                    ) ) { // Tutores
            //getMenuInflater().inflate(R.menu.menu_tutores, menu);
            tvBienvenida.setText( "Bienvenid@ " + singleton.getNombreCompletoUsuario() );
            dbHijos cm = new dbHijos(this, this);

            if ( singleton.getRsHijosSize() <= 0) {
                cm.obtenerDatos();
            }else{
                cm.resfreshHijos();
            }

        }else if ( session.isLoggedIn() &&
                (singleton.getIdUserNivelAcceso() == 6 ||
                        singleton.getIdUserNivelAcceso() == 18 ||
                        singleton.getIdUserNivelAcceso() == 23 ) ) {
            tvBienvenida.setText( singleton.getNombreCompletoUsuario() + " "  );
            callAdapter(true);

        }else if ( session.isLoggedIn() && singleton.getIdUserNivelAcceso() == 5) { // Alu
            tvBienvenida.setText( singleton.getNombreCompletoUsuario() + " "  );
            callAdapter(true);
        }

        return true;

    }

    private void callAdapter(boolean isNull){

        listaMM = (RecyclerView) findViewById(R.id.rvHijos);
        LinearLayoutManager llm;
        if (isNull){
            llm = new LinearLayoutManager(this);
        }else{
            llm = new LinearLayoutManager(null);
        }
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMM.setLayoutManager(llm);
        Adapter_Menu_Tutores mad = new Adapter_Menu_Tutores(this);
        listaMM.setAdapter(mad);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e("ENTRO??","SI 10");
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Log.e("ENTRO??","SI 20");
        int id = item.getItemId();
        DocumentInside bi = new DocumentInside(webview,this);
        String URL = "";
        int Type = 0;
        switch (id){
            case R.id.nav_inicio:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.nav_cerrar_session:
                logoutUser();
                return true;
            case R.id.nav_boletin:
                drawer.closeDrawer(GravityCompat.START);
                return  bi.onCreateBoletin();
            case R.id.nav_quienes_somos:
                URL = AppConfig.URL_QUIENES_SOMOS;
                Type = AppConfig.URL_QUIENES_SOMOS_TYPE;
                break;
            case R.id.nav_certificaciones:
                URL = AppConfig.URL_CERTIFICACIONES;
                Type = AppConfig.URL_CERTIFICACIONES_TYPE;
                break;
            case R.id.nav_directorio:
                URL = AppConfig.URL_DIRECTORIO;
                Type = AppConfig.URL_DIRECTORIO_TYPE;
                break;
            case R.id.nav_proceso_admision:
                URL = AppConfig.URL_PROCESO_ADMISION;
                Type = AppConfig.URL_PROCESO_ADMISION_TYPE;
                break;
            case R.id.nav_mapa:
                Intent intentMap = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intentMap);
                return true;
            case R.id.nav_beneficios:
                URL = AppConfig.URL_BENEFICIOS;
                Type = AppConfig.URL_BENEFICIOS_TYPE;
                break;
            case R.id.nav_aviso_privacidad:
                URL = AppConfig.URL_AVISO_PRIVACIDAD;
                Type = AppConfig.URL_AVISO_PRIVACIDAD_TYPE;
                break;
            case R.id.nav_desarrollador:
                URL = AppConfig.URL_DESARROLLADOR;
                Type = AppConfig.URL_DESARROLLADOR_TYPE;
                break;
            case R.id.nav_system:
                Intent intentAS = new Intent(MainActivity.this, SystemActivity.class);
                startActivity(intentAS);
                return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        return  bi.onCreateObject(URL, Type);

    }


    public void MensajesClick(View v){
        Intent intent = new Intent(this, ListaElementos.class);
        intent.putExtra(this.getString(R.string.menu), "NOTIFICACIONES");
        intent.putExtra(this.getString(R.string.idmenu), INDICE_NOTIFICACIONES);
        this.startActivity(intent);

    }

    public void CircularesClick(View v){
        Intent intent = new Intent(this, ListaElementos.class);
        intent.putExtra(this.getString(R.string.menu), "CIRCULAR");
        intent.putExtra(this.getString(R.string.idmenu), INDICE_CIRCULAR);
        this.startActivity(intent);

    }

    public void FacturasClick(View v){
        Intent intent = new Intent(this, ListaElementos.class);
        intent.putExtra(this.getString(R.string.menu), "FACTURAS");
        intent.putExtra(this.getString(R.string.idmenu), INDICE_FACTURAS);
        this.startActivity(intent);

    }

    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        Singleton.reset();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mx.com.logydes.colegioarji/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mx.com.logydes.colegioarji/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    @Override
    protected void onResume() {
        super.onResume();
        webview.reload();
    }

}
