package mx.com.logydes.colegioarji.RestAPI;

import java.util.HashMap;

import mx.com.logydes.colegioarji.Helper.Singleton;
import mx.com.logydes.colegioarji.RestAPI.Model.TareasResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by devch on 29/09/16.
 */

public interface EndPointsAPI {

    public String Username = Singleton.getUsername();

    @POST(ConstantesRestAPI.TAREAS)
    Call<TareasResponse> getRecentMedia(@Body HashMap<String, String> params);

}
