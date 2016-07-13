package mx.com.logydes.colegioarji.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.MenuHijos;
import mx.com.logydes.colegioarji.Pojos.Hijos;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.Utils.Utilidades;

/**
 * Created by devch on 23/06/16.
 */

public class AdapterHijos extends RecyclerView.Adapter<AdapterHijos.AdapterHijosViewHolder> {
    private static final String TAG = "RESPUESTA MM";
    private ProgressDialog pDialog;
    private Utilidades Utl;


    private ArrayList<Hijos> MM;
    private Activity activity;
    private Context context;
    // private dbHijos cm;

    public AdapterHijos(Activity activity){
        this.MM = Singleton.getRsHijos(); // new ArrayList<Hijos>();
        Log.e(TAG,String.valueOf(Singleton.getRsHijos()));
        this.activity = activity;
        // Log.e(TAG, String.valueOf(MM.size()));
    }

    // Infla el Layout y lo pasa a cada elemento para que obtenga los view
    @Override
    public AdapterHijosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_hijos, parent, false);
        return new AdapterHijosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterHijosViewHolder cvh, int position) {

        final Hijos mm = MM.get(position);

        cvh.tvHijo.setText(mm.getLabel());
        cvh.data = mm.getData();
        cvh.tvGrupo.setText(mm.getGrupo());
        cvh.IdUserAlu = mm.getIdUserAlu();
        cvh.IdGruAlu = mm.getIdGruAlu();
        cvh.urlBoleta = mm.getUrlBoleta();
        cvh.logoEmp = mm.getLogoEmp();
        cvh.logoIB = mm.getLogoIB();
        cvh.IsBoleta = mm.getIsBoleta();

        // Log.e("IsBoleta: ", String.valueOf(cvh.IsBoleta));

        // Log.e("IdGruAlu cvh: ", String.valueOf(mm.getIdGruAlu()));

        cvh.lyHijos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Singleton.setIdAlu(mm.getData());
                    Singleton.setIdUserAlu(mm.getIdUserAlu());
                    Singleton.setIdGruAlu(mm.getIdGruAlu());

                    Log.e("IdGruAlu Singleton: ", String.valueOf(Singleton.getIdGruAlu()));

                    Intent intent = new Intent(activity, MenuHijos.class);
                    intent.putExtra(activity.getString(R.string.nombreAlumno), mm.getLabel());
                    intent.putExtra(activity.getString(R.string.idalu), Singleton.getIdAlu());
                    intent.putExtra("grupo", mm.getGrupo());
                    intent.putExtra(activity.getString(R.string.IdUserAlu), mm.getIdUserAlu());
                    intent.putExtra(activity.getString(R.string.IdGruAlu), mm.getIdGruAlu());
                    intent.putExtra(activity.getString(R.string.urlBoleta), mm.getUrlBoleta());
                    intent.putExtra(activity.getString(R.string.logoEmp), mm.getLogoEmp());
                    intent.putExtra(activity.getString(R.string.logoIB), mm.getLogoIB());
                    intent.putExtra(activity.getString(R.string.IsBoleta), mm.getIsBoleta());
                    activity.startActivity(intent);

            }
        });

    }

    public int getItemCount() {
        return MM.size();
    }

    public static class AdapterHijosViewHolder extends RecyclerView.ViewHolder{

        LinearLayout lyHijos;
        TextView tvHijo;
        TextView tvGrupo;
        int data;
        int IdUserAlu;
        String urlBoleta;
        String logoEmp;
        String logoIB;
        int IsBoleta;
        int IdGruAlu;

        public AdapterHijosViewHolder(View itemView) {
            super(itemView);
            data = 0;
            IdUserAlu = 0;
            urlBoleta = "";
            logoEmp = "";
            logoIB = "";
            IsBoleta = 0;
            IdGruAlu = 0;
            tvHijo = (TextView) itemView.findViewById(R.id.tvHijo);
            tvGrupo = (TextView) itemView.findViewById(R.id.tvGrupo);
            lyHijos = (LinearLayout) itemView.findViewById(R.id.lyHijos);

        }

    }

}