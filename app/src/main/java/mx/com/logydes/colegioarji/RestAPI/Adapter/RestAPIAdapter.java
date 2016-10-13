package mx.com.logydes.colegioarji.RestAPI.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.logydes.colegioarji.RestAPI.ConstantesRestAPI;
import mx.com.logydes.colegioarji.RestAPI.Deserializador.TareaDeserializador;
import mx.com.logydes.colegioarji.RestAPI.EndPointsAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by devch on 29/09/16.
 */

public class RestAPIAdapter {

    public EndPointsAPI establecerConexionPlatSource(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsAPI.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConstantesRestAPI.class, new TareaDeserializador());
        return gsonBuilder.create();
    }

}
