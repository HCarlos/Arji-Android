package mx.com.logydes.colegioarji.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.logydes.colegioarji.Boleta;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Inside.DocumentInside;
import mx.com.logydes.colegioarji.ListaElementos;
import mx.com.logydes.colegioarji.Pojos.Menu_Tutores;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.Utils.Utilidades;

/**
 * Created by devch on 24/06/16.
 */

    public class Adapter_Menu_Tutores extends RecyclerView.Adapter<Adapter_Menu_Tutores.Adapter_Menu_TutoresViewHolder> {
        private static final String TAG = "RESPUESTA MM";
        private ProgressDialog pDialog;
        private Utilidades Utl;

        private ArrayList<Menu_Tutores> MM;
        private Activity activity;
        private Context context;

        // private db_Menu_Tutores cm;

        public Adapter_Menu_Tutores(Activity _activity){
            this.activity = _activity;

            // Log.e(TAG, String.valueOf(MM.size()));
            MM = new ArrayList<Menu_Tutores>();
            MM.add(new Menu_Tutores(0,"Tareas"));
            MM.add(new Menu_Tutores(1,"Circulares"));
            MM.add(new Menu_Tutores(2,"Facturas"));
            MM.add(new Menu_Tutores(3,"Pagos"));
            MM.add(new Menu_Tutores(4,"Boletas"));


        }

        // Infla el Layout y lo pasa a cada elemento para que obtenga los view
        @Override
        public Adapter_Menu_TutoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_menu_tutores, parent, false);
            return new Adapter_Menu_TutoresViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final Adapter_Menu_TutoresViewHolder cvh, int position) {

            final Menu_Tutores mm = MM.get(position);

            cvh.tvMenu.setText(mm.getMenu() );
            cvh.idmenu = mm.getIdmenu();


            // Log.e(TAG,mm.getGrupo());en

            cvh.lyTutores.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cvh.idmenu <= 3) {
                        Intent intent = new Intent(activity, ListaElementos.class);
                        intent.putExtra(activity.getString(R.string.menu), mm.getMenu());
                        intent.putExtra(activity.getString(R.string.idmenu), cvh.idmenu);
                        activity.startActivity(intent);
                    }
                    if (cvh.idmenu == 4) {
                        if (Singleton.getIsBoleta() > 0) {

                            Intent intent = new Intent(activity, Boleta.class);
                            intent.putExtra(activity.getString(R.string.Username), Singleton.getUsername());
                            intent.putExtra(activity.getString(R.string.IdUser), Singleton.getIdUser());
                            intent.putExtra(activity.getString(R.string.IdUserAlu), Singleton.getIdUserAlu());
                            intent.putExtra(activity.getString(R.string.IdEmp), Singleton.getIdEmp());

                            intent.putExtra(activity.getString(R.string.urlBoleta), Singleton.getUrlBoleta());
                            intent.putExtra(activity.getString(R.string.logoEmp), Singleton.getLogoEmp());
                            intent.putExtra(activity.getString(R.string.logoIB), Singleton.getLogoIB());
                            intent.putExtra(activity.getString(R.string.IsBoleta), Singleton.getIsBoleta());
                            intent.putExtra(activity.getString(R.string.IdGruAlu), Singleton.getIdGruAlu());


                            activity.startActivity(intent);
                        }else{
                            Toast.makeText(activity, "Boleta no esta disponible", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            });

        }

    /*
    public String getLikes(_Menu_Tutores mm){
        cm = new db_Menu_Tutores(activity);
        String likes = String.valueOf( cm.getLikes(mm) );
        return String.valueOf( cm.getLikes(mm) );
    }
    */

        public int getItemCount() {

            // Log.e(TAG+" TOTAL",String.valueOf(MM.size()));
            return MM.size();
        }

        public static class Adapter_Menu_TutoresViewHolder extends RecyclerView.ViewHolder{

            LinearLayout lyTutores;
            TextView tvMenu;
            int idmenu;

            public Adapter_Menu_TutoresViewHolder(View itemView) {
                super(itemView);
                idmenu = 0;
                tvMenu = (TextView) itemView.findViewById(R.id.tvMenu);
                lyTutores = (LinearLayout) itemView.findViewById(R.id.lyTutores);

            }

        }

    }