package mx.com.logydes.colegioarji.RestAPI.Deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;

import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;
import mx.com.logydes.colegioarji.RestAPI.Model.TareasResponse;

import static java.lang.String.valueOf;

/**
 * Created by devch on 29/09/16.
 */

public class TareaDeserializador implements JsonDeserializer<TareasResponse> {
    @Override
    public TareasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.w("Hasta aqui entro","bien 0");
        Gson gson = new Gson();
        TareasResponse tareasResponse = gson.fromJson(json, TareasResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray();
        tareasResponse.setLista_elementoses(deserializarContactoDeJson(contactoResponseData));

        return tareasResponse;

    }

    private ArrayList<Lista_Elementos> deserializarContactoDeJson(JsonArray contactoResponseData){

        ArrayList<Lista_Elementos> tareas = new ArrayList<>();

        Log.w("Hasta aqui entro","bien 1");

        for (int i = 0; i < contactoResponseData.size() ; i++) {

            Log.w("Hasta aqui entro bien 1 - ", String.valueOf(i));

            JsonObject rec = contactoResponseData.get(i).getAsJsonObject();

            int idelemento              = rec.get("idtarea").getAsInt();
            int idelementodestinatario  = rec.get("idtareadestinatario").getAsInt();
            String label                = rec.get("titulo_tarea").getAsString();

            tareas.add( new Lista_Elementos(label,idelemento,idelementodestinatario, 0) );

        }
        return tareas;
    }

}