package mx.com.logydes.colegioarji.Utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import mx.com.logydes.colegioarji.ListaElementos;
import mx.com.logydes.colegioarji.MainActivity;
import mx.com.logydes.colegioarji.R;

import static mx.com.logydes.colegioarji.Utils.AppConfig.INDICE_NOTIFICACIONES;

/**
 * Created by devch on 6/10/16.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "NOTIF: ";
    private Context activity;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        activity = getApplicationContext();

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


/*
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                    activity).setSmallIcon(R.drawable.icon_notification_transparent)
                    .setContentTitle("Test Notification")
                    .setContentText("This is test notification ");
            Intent myIntent = new Intent(activity, ListaElementos.class);
            PendingIntent intent2 = PendingIntent.getBroadcast(activity, 1,
                    myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(intent2);
            NotificationManager mNotificationManager = (NotificationManager) activity
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(1, notificationBuilder.build());
*/


        }

        // FirebaseMessaging.getInstance().subscribeToTopic("AndroidMessage");

       enviarNotificacion(remoteMessage);



    }




    public void enviarNotificacion(RemoteMessage remoteMessage){

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String Titulo = remoteMessage.getNotification().getTitle();

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon_notification_transparent);

        if ( Titulo.isEmpty() ) Titulo = getString(R.string.app_name);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_notification_transparent)
                .setLargeIcon(bm)
                .setContentTitle(Titulo)
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                ;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }


}
