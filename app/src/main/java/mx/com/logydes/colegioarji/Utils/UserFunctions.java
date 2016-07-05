package mx.com.logydes.colegioarji.Utils;

/**
 * Created by devch on 15/06/16.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

// import com.techiequickie.bharath.boadraf.Loginactivity;
// import com.techiequickie.bharath.boadraf.choose_task;

public class UserFunctions
{
    private JSONParser jsonParser;

    public static String loginURL = "http://platsource.mx/getLoginUserMobile/";
    private static String registerURL = "http://techiequickie.com/android_login_api/";

    private static String login_tag = "login";
    private static String register_tag = "register";

    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }


    private class myAsynTask extends AsyncTask <URL, Void, String >
    {


        @Override
        protected String doInBackground(URL... params)
        {
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("tag", login_tag));
            param.add(new BasicNameValuePair("email", "no"));
            param.add(new BasicNameValuePair("password", "ne"));
            // JSONObject json = jsonParser.getJSONFromUrl(loginURL);

            return "";
        }

        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                //Intent i = new Intent(UserFunctions.this,choose_task.class);
                // startActivity(i);
            }

        }
    }

    /**
     * function make Login Request
     * @param email
     * @param password
     *
    public JSONObject loginUser(String email, String password){
    // Building Parameters
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("tag", login_tag));
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("password", password));
    JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
    // return json
    // Log.e("JSON", json.toString());
    return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password,String preffered_username){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("preffered", preffered_username));


        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL);
        // return json
        return json;
    }

    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        /*
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        */
        return false;
    }

    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        /*
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        */
        return true;
    }



}
