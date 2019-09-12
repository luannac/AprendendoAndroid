package com.luann.formativa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadCastSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Bundle class is used to recovery all extra data from intent
        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        SmsMessage[] sms = new SmsMessage[pdus.length];

        String  conteudoSMS = "";

        for (int i=0;i<sms.length;i++){
            sms[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            conteudoSMS += sms[i].getMessageBody();
            }
        int ind = conteudoSMS.indexOf(':');
        if(ind != -1){
            String chave = conteudoSMS.substring(ind+1,ind+7);

            if(!new BancoDeDados(context,1).chaveExiste(chave)){
                Toast.makeText(context,
                        "Chave "+chave+" não existe!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
