package mx.com.logydes.colegioarji.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;

import mx.com.logydes.colegioarji.Boleta;
import mx.com.logydes.colegioarji.Elemento;
import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Inside.DocumentInside;
import mx.com.logydes.colegioarji.ListaElementos;
import mx.com.logydes.colegioarji.Pojos.Menu_Tutores;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.Utils.AppConfig;
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
        if ( Singleton.getIdUserNivelAcceso() == 7 ) {
            MM.add(new Menu_Tutores(0, "Tareas"));
            MM.add(new Menu_Tutores(1, "Circulares"));
            MM.add(new Menu_Tutores(2, "Facturas"));
            MM.add(new Menu_Tutores(3, "Pagos"));
            MM.add(new Menu_Tutores(4, "Boletas"));
            MM.add(new Menu_Tutores(5, "Calendario"));
        }

        if (
                Singleton.getIdUserNivelAcceso() == 3 ||
                Singleton.getIdUserNivelAcceso() == 6 ||
                Singleton.getIdUserNivelAcceso() == 18 ||
                Singleton.getIdUserNivelAcceso() == 23
            ) {

            MM.add(new Menu_Tutores(1, "Circulares"));

        }

        if ( Singleton.getIdUserNivelAcceso() == 5 ) {
            MM.add(new Menu_Tutores(0, "Tareas"));
            MM.add(new Menu_Tutores(1, "Circulares"));
            MM.add(new Menu_Tutores(4, "Boletas"));
            MM.add(new Menu_Tutores(5, "Calendario"));
            Singleton.setIdGruAlu( Singleton.getIdUser() );
            Singleton.setIdUserAlu( Singleton.getIdUser() );
            Singleton.setIsBoleta(1);
        }

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
                if (cvh.idmenu <= 3 ) {
                    Intent intent = new Intent(activity, ListaElementos.class);
                    intent.putExtra(activity.getString(R.string.menu), mm.getMenu());
                    intent.putExtra(activity.getString(R.string.idmenu), cvh.idmenu);
                    activity.startActivity(intent);
                }
                if (cvh.idmenu == 4) {
                    if (Singleton.getIsBoleta() > 0) {

/*
                            Intent intent = new Intent(activity, Boleta.class);
                            intent.putExtra(activity.getString(R.string.Username), Singleton.getUsername());
                            intent.putExtra(activity.getString(R.string.IdUser), Singleton.getIdUser());
                            intent.putExtra(activity.getString(R.string.IdUserAlu), Singleton.getIdUserAlu());
                            intent.putExtra(activity.getString(R.string.IdEmp), Singleton.getIdEmp());

                            intent.putExtra(activity.getString(R.string.urlBoleta), Singleton.getUrlBoleta());
                            intent.putExtra(activity.getString(R.string.logoEmp), Singleton.getLogoEmp());
                            intent.putExtra(activity.getString(R.string.logoIB), Singleton.getLogoIB());
                            intent.putExtra(activity.getString(R.string.IsBoleta), Singleton.getIsBoleta());
                            intent.putExtra(activity.getString(R.string.IdGruAlu), Singleton.getIdUserAlu());


                            activity.startActivity(intent);
*/



                        String url = "http://platsource.mx/php/01/mobile/boletas_layout.php?idgrualu="+Singleton.getIdUserAlu()+"&user="+Singleton.getUsername()+"&iduser="+Singleton.getIdUser()+"&idemp="+Singleton.getIdEmp();

                        // String url = Singleton.getUrlBoleta();

                        Log.e("URL BOLETA",url);


                        if (!url.startsWith("http://") && !url.startsWith("https://"))
                            url = "http://" + url;

                        final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));

                        String nc = "u="+Singleton.getUsername()+
                                "&strgrualu="+Singleton.getIdUserAlu()+
                                "&logoEmp="+Singleton.getLogoEmp()+
                                "&logoIbo="+Singleton.getLogoIB();
                        String s = " order by orden_impresion asc ";

                        Log.e("NC",nc);


                        intent.putExtra("o","0");
                        intent.putExtra("t","40");
                        intent.putExtra("c",nc);
                        intent.putExtra("from","0");
                        intent.putExtra("cantidad","0");
                        intent.putExtra("s", s);

                        activity.startActivity(intent);
                        // getActivity().startActivity(intent);



                            /*

                            String nc = "u="+Singleton.getUsername()+
                                    "&strgrualu="+Singleton.getIdUserAlu()+
                                    "&logoEmp="+Singleton.getLogoEmp()+
                                    "&logoIbo="+Singleton.getLogoIB();

                            String s = " order by orden_impresion asc ";

                            String postData = "o=0&t=40&c=0&from=0&cantidad=0&s="+s+nc;

                            String params = URLEncoder.encode(postData);
                            String url = Singleton.getUrlBoleta()+ "?" + params;

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            activity.startActivity(browserIntent);

                            */


                    }else{
                        Toast.makeText(activity, "Boleta no esta disponible", Toast.LENGTH_LONG).show();
                    }

                }

                if (cvh.idmenu == 5) {
                    Intent intent = new Intent(activity, Elemento.class);
                    intent.putExtra(activity.getString(R.string.menuelemento), mm.getMenu() );
                    intent.putExtra(activity.getString(R.string.tipoelemento), cvh.idmenu );
                    intent.putExtra(activity.getString(R.string.elemento), mm.getMenu() );
                    activity.startActivity(intent);
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