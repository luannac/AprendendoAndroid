package com.luann.formativa;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notificacoes {
    private Context context;
    private int CODIGO_NOTIFICACAO = 613;
    private int CODIGO_NOTIFICACAO_RESPOSTA = 445;
    NotificationManager servico = null;

    public Notificacoes(Context context){
        super();
        this.context =context;
    }

    public void notificaoRecebimento(String msg){
        servico =(NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, TelaDasChaves.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icone_notificacao)
                .setContentTitle("Chave Recebida")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        servico.notify(230, builder.build());
    }
}
