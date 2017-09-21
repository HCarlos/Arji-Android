package mx.com.logydes.colegioarji;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import mx.com.logydes.colegioarji.Adapter.AdapterHijos;
import mx.com.logydes.colegioarji.Adapter.Adapter_Menu_Tutores;
import mx.com.logydes.colegioarji.Helper.Singleton;

public class MenuHijos extends AppCompatActivity {

    private Context context;
    private RecyclerView listaMM0;
    private RecyclerView listaMM;
    private AdapterHijos mad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_hijos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle params = getIntent().getExtras();

        listaMM = (RecyclerView) findViewById(R.id.rvMenu);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMM.setLayoutManager(llm);

        String Alumno = params.getString(getResources().getString(R.string.nombreAlumno));
        int IdAlu = params.getInt(getResources().getString(R.string.idalu));
        int IdUserAlu = params.getInt(getResources().getString(R.string.IdUserAlu));
        String urlBoleta = params.getString(getResources().getString(R.string.urlBoleta));
        String logoEmp = params.getString(getResources().getString(R.string.logoEmp));
        String logoIB = params.getString(getResources().getString(R.string.logoIB));
        int IsBoleta = params.getInt(getResources().getString(R.string.IsBoleta));
        int IdGruAlu = params.getInt(getResources().getString(R.string.IdGruAlu));

        Singleton.setIdUserAlu(IdUserAlu);
        Singleton.setUrlBoleta(urlBoleta);
        Singleton.setLogoEmp(logoEmp);
        Singleton.setLogoIB(logoIB);
        Singleton.setIsBoleta(IsBoleta);
        Singleton.setIdGruAlu(IdGruAlu);

        Log.e("IdGruAlu: ",String.valueOf(Singleton.getIdGruAlu()));

        Adapter_Menu_Tutores mad = new Adapter_Menu_Tutores(this);
        listaMM.setAdapter(mad);

        this.setTitle(Alumno);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
