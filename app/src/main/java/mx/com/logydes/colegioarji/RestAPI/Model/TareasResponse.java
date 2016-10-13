package mx.com.logydes.colegioarji.RestAPI.Model;

import java.util.ArrayList;

import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;

/**
 * Created by devch on 29/09/16.
 */

public class TareasResponse {
    ArrayList<Lista_Elementos> lista_elementoses;

    public ArrayList<Lista_Elementos> getLista_elementoses() {
        return lista_elementoses;
    }

    public void setLista_elementoses(ArrayList<Lista_Elementos> lista_elementoses) {
        this.lista_elementoses = lista_elementoses;
    }
}
