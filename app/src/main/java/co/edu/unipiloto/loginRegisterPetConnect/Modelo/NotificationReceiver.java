package co.edu.unipiloto.loginRegisterPetConnect.Modelo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import co.edu.unipiloto.loginRegisterPetConnect.R;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String titulo = intent.getStringExtra("titulo");
        String mensaje = intent.getStringExtra("mensaje");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CITAS_CHANNEL")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        Intent actividadIntent = new Intent(context, RecordatoriosActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, actividadIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1001, builder.build());
        }
    }
}
