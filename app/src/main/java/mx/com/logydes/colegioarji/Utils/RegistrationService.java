package mx.com.logydes.colegioarji.Utils;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

import mx.com.logydes.colegioarji.R;

/**
 * Created by devch on 11/01/17.
 */

public class RegistrationService extends IntentService {
    public RegistrationService() {
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID myID = InstanceID.getInstance(this);

        Log.e("Error:=> ",String.valueOf(R.string.gcm_defaultSenderId) );


        try {
            String registrationToken = myID.getToken(
                    String.valueOf(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );
            Log.e("Registration Token DEVCH ", registrationToken);

            GcmPubSub subscription = GcmPubSub.getInstance(this);
            subscription.subscribe(registrationToken, "/topics/my_little_topic", null);

        } catch (IOException e) {
            e.printStackTrace();
        }



/*
        String authorizedEntity = "firebase-carji"; // Project id from Google Developer Console
        String scope = "GCM"; // e.g. communicating using GCM, but you can use any
        // URL-safe characters up to a maximum of 1000, or
        // you can also leave it blank.
        String token = InstanceID.getInstance(this).getToken(authorizedEntity,scope);
*/

    }


}