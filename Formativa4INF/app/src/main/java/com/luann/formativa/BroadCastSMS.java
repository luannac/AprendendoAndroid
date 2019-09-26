package com.luann.formativa;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.constraintlayout.widget.Constraints;
import androidx.core.content.res.ResourcesCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BroadCastSMS extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        //Bundle class is used to recovery all extra data from intent
        BancoDeDados db = new BancoDeDados(context,1);
        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        SmsMessage[] sms = new SmsMessage[pdus.length];
        String chave = null;

        String  conteudoSMS = "";

        for (int i=0;i<sms.length;i++){
            sms[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            conteudoSMS += sms[i].getMessageBody();
            }
        int ind = conteudoSMS.indexOf(':');
        if(ind != -1){
            chave = conteudoSMS.substring(ind+1,ind+7);

            if(!db.chaveExiste(chave)){
                Toast.makeText(context,
                        "Chave "+chave+" não existe!",
                        Toast.LENGTH_LONG).show();
                }
            }else{
                if(db.chaveUtilizada(chave)){
                    Toast.makeText(context,
                            "Chave já Utilizada",
                            Toast.LENGTH_LONG).show();
                }else{
                    db.atualizachave(chave,true);
                    Chave chaveO = db.getChave(chave);


                }
            }
    }
}
