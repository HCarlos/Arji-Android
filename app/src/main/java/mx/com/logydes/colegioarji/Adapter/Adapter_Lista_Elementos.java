package mx.com.logydes.colegioarji.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Elemento;
import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;
import mx.com.logydes.colegioarji.R;
import mx.com.logydes.colegioarji.Utils.Utilidades;

/**
 * Created by devch on 24/06/16.
 */
public class Adapter_Lista_Elementos extends RecyclerView.Adapter<Adapter_Lista_Elementos.AdapterElementosViewHolder> {
        private static final String TAG = "RESPUESTA MM";
        private ProgressDialog pDialog;
        private Utilidades Utl;


        private ArrayList<Lista_Elementos> MM;
        private Activity activity;
        private Context context;
        private String menu;
        // private dbElementos cm;

        public Adapter_Lista_Elementos(Activity activity, String _Menu){
            this.MM = Singleton.getRsElementos(); // new ArrayList<Lista_Elementos>();
            this.activity = activity;
            this.menu = _Menu;
            // Log.e(TAG, String.valueOf(MM.size()));
        }

        // Infla el Layout y lo pasa a cada elemento para que obtenga los view
        @Override
        public AdapterElementosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_elementos, parent, false);
            return new AdapterElementosViewHolder(v,this.menu);
        }

        @Override
        public void onBindViewHolder(final AdapterElementosViewHolder cvh, int position) {

            final Lista_Elementos mm = MM.get(position);
            boolean primerElemento = false;
            int elementoEncontrado = -1;

            cvh.TipoElemento = mm.getTipo();
            switch (cvh.TipoElemento){
                case 0:
                    cvh.tvElemento.setText(mm.getLabel() );
                    cvh.IdElemento = mm.getIdTarea();
                    cvh.IdElementoDestinatario = mm.getIdTareaDestinatario();
                    cvh.tvElemento_Detail.setText(mm.getProfesor() );
                    break;
                case 1:
                    cvh.tvElemento.setText(mm.getLabel() );
                    cvh.IdElemento = mm.getIdComMensaje();
                    cvh.IdElementoDestinatario = mm.getIdComMensajeDestinatario();
                    cvh.tvElemento_Detail.setText(mm.getDirector() );
                    break;
                case 2:
                    cvh.tvElemento.setText(mm.getPDF() );
                    cvh.IdElemento = mm.getIdFactura();
                    cvh.PDF = mm.getPDF();
                    cvh.XML = mm.getXML();
                    cvh.tvElemento_Detail.setText(mm.getXML() );
                    cvh.Directorio = mm.getDirectorio();
                    break;
                case 3:
                    cvh.Vencido = mm.getVencido();
                    cvh.Status_Movto = mm.getStatus_movto();
                    cvh.IdConcepto = mm.getIdConcepto();
                    cvh.tvElemento_Detail.setText("");
                    cvh.FechaPago = mm.getFechaPago();
                    cvh.Vencimiento = mm.getVencimiento();

                    String[] fecha = cvh.FechaPago.substring(0,10).split("-");
                    String FechaPago = fecha[2]+"-"+fecha[1]+"-"+fecha[0]+" "+cvh.FechaPago.substring(11,cvh.FechaPago.length());

                    fecha = cvh.Vencimiento.split("-");
                    String Vencimiento = fecha[2]+"-"+fecha[1]+"-"+fecha[0];

                    if (cvh.Vencido < 0 && cvh.Status_Movto == 0) {
                        cvh.tvElemento.setText(mm.getLabel() + "(Vencido)");
                        cvh.tvElemento_Detail.setText("Vencido el " + Vencimiento );
                        cvh.tvElemento_Detail.setTextColor(R.color.backgroud_text);
                        cvh.tvElemento_Detail.setTypeface(null, Typeface.BOLD);
                    }else{
                        if ( cvh.Status_Movto == 1 ) {
                            cvh.tvElemento.setText(mm.getLabel());
                            cvh.tvElemento_Detail.setText("Pagado el " + FechaPago );
                            // cvh.tvElemento_Detail.setTextColor(R.color.colorAccent);
                        }else{
                            cvh.tvElemento.setText(mm.getLabel() );
                        }
                    }

                    if ( cvh.IdConcepto > 0 ) {
                        cvh.imgRightElements.setVisibility(View.VISIBLE);
                    }else{
                        cvh.imgRightElements.setVisibility(View.INVISIBLE);
                    }

                    cvh.IdElemento = mm.getIdElemento();
                    cvh.Concepto = mm.getConcepto();
                    cvh.Mes = mm.getMes();
                    cvh.FechaPago = mm.getFechaPago();
                    break;
            }


            cvh.lyElementos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, Elemento.class);
                    intent.putExtra(activity.getString(R.string.elemento), cvh.tvElemento.getText() );
                    intent.putExtra(activity.getString(R.string.idelemento), cvh.IdElemento );
                    intent.putExtra(activity.getString(R.string.idelementodestinatario), cvh.IdElementoDestinatario );
                    intent.putExtra(activity.getString(R.string.menuelemento), cvh.Menu );
                    intent.putExtra(activity.getString(R.string.tipoelemento), cvh.TipoElemento );
                    intent.putExtra(activity.getString(R.string.PDF), cvh.PDF );
                    intent.putExtra(activity.getString(R.string.Directorio), cvh.Directorio );
                    intent.putExtra(activity.getString(R.string.Mes), cvh.Mes );
                    intent.putExtra(activity.getString(R.string.Concepto), cvh.Concepto );
                    intent.putExtra(activity.getString(R.string.FechaPago), cvh.FechaPago );
                    intent.putExtra(activity.getString(R.string.Status_Movto), cvh.Status_Movto );
                    intent.putExtra(activity.getString(R.string.Vencido), cvh.Vencido );
                    intent.putExtra(activity.getString(R.string.IdConcepto), cvh.IdConcepto );
                    intent.putExtra(activity.getString(R.string.PagosDiv), cvh.PagosDiv );

                    activity.startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return MM.size();
        }

        public static class AdapterElementosViewHolder extends RecyclerView.ViewHolder{

            LinearLayout lyElementos;
            TextView tvElemento;
            TextView tvElemento_Detail;
            ImageView imgRightElements;
            int IdElemento;
            int IdElementoDestinatario;
            int TipoElemento;
            String Menu;
            String PDF;
            String XML;
            String Directorio;
            String Concepto;
            String Mes;
            String FechaPago;
            String Vencimiento;
            int Status_Movto;
            int Vencido;
            int IdConcepto;
            int PagosDiv;
            String lblProfesor;
            String lblDirector;

            public AdapterElementosViewHolder(View itemView, String _menu) {
                super(itemView);
                IdElemento = 0;
                IdElementoDestinatario = 0;
                TipoElemento = 0;
                Menu = _menu;
                PDF = "";
                XML = "";
                Directorio = "";
                Concepto = "";
                Mes = "";
                FechaPago = "";
                Vencimiento = "";
                Status_Movto = 0;
                Vencido = 0;
                IdConcepto = 0;
                PagosDiv = 0;
                tvElemento = (TextView) itemView.findViewById(R.id.tvElemento);
                lyElementos = (LinearLayout) itemView.findViewById(R.id.lyElementos);
                imgRightElements = (ImageView) itemView.findViewById(R.id.imgRightElements);
                tvElemento_Detail = (TextView) itemView.findViewById(R.id.tvElemento_Detail);

            }

        }

    }