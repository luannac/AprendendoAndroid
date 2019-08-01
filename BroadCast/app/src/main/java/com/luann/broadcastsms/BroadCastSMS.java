package com.luann.broadcastsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadCastSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Bundle class is used to recovery all extra data from intent
        Bundle extras = intent.getExtras();

        //search in extras just "PDU" type - Protocol Description Unit (SMS)
        Object[] pdus = (Object[]) extras.get("pdus");

        //SmsMessage class used to manipulate data from pdus
        SmsMessage[] sms = new SmsMessage[pdus.length];

        String numeroRemetente = "", conteudoSMS = "";

        //Loop for read each SMS/PDUs
        for (int i=0;i<sms.length;i++){
            //Create a message SMS from PDU
            //the first pdu is set as first in vector
            sms[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

            //join the SMSs
            conteudoSMS += sms[i].getMessageBody();

            //Recovery the number address of the SMS
            numeroRemetente = sms[i].getOriginatingAddress();

            Toast.makeText(context,
                    "MSG: "+conteudoSMS+" Número: "+ numeroRemetente,
                    Toast.LENGTH_LONG).show();
        }
    }
}
