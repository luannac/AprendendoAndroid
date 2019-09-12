package com.example.broadcastsms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadcastSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //Utilizar a classe Bundle para recuperar todos os dados extras da intent

        Bundle extras = intent.getExtras();

        //PDU (SMS) - Protocol Description Unit
        //Filtar o "extras" para listar somente o tipo "PDU"

        Object[] pdus = (Object[]) extras.get("pdus");//quando não se sabe o tamanho do sms que a pessoa irá mandar, por isso Object está com colchetes vazios

        //Utilizar a classe SmsSessage do Android para poder manipular os dados com os métodos da classe

        SmsMessage[] sms = new SmsMessage[pdus.length];
        String numeroRemetente = "", conteudoSMS = "";

        String conteudoMensagem = "";
        long datahora = 0;
        //Laço para percorer cada SMS/PDUs
        for(int i=0; i<sms.length; i++){
            //Criando uma mensagem SMS à parit do PDU
            sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

            //Concatenando o conteúdo do SMS na variável conteudoSMS
           // conteudoSMS += sms[i].getMessageBody();

            //Recuperando o número de quem enviou o SMS
            numeroRemetente = sms[i].getDisplayOriginatingAddress();
            datahora = sms[i].getTimestampMillis();
            conteudoMensagem+= sms[i].getMessageBody();


        }//fim do laço for

        //separar latitude e longitude
        String[]coordenadas = conteudoMensagem.split(";");
                double latitude = Double.parseDouble(coordenadas[0]);
                double longitude = Double.parseDouble(coordenadas[1]);

        BancoDeDados bd = new BancoDeDados(context, 1);
        if(bd.cadastraPontos(latitude, longitude, datahora)){
            Toast.makeText(context, "Cadastrado", Toast.LENGTH_LONG).show();

            //Criação da intent para indicar qual recurso será aberto ao clicar sobre a notificação
            Intent it = new Intent(context,MainActivity.class);

            //Transformar em PendinfIntent visto que a main não será aberta
            PendingIntent pending = PendingIntent.getActivity(context,
                    0,          //Zero por padrão
                    it,                     //Intent que será transformada em pedingIntent
                    PendingIntent.FLAG_CANCEL_CURRENT);//<Modo de operação
            //Casos haja a mesma notificação do mesmo aplicativo, cancela
            //a anterior e atualiza com o conteudo

            //Criação e configuração da notificação
            Notification.Builder notificacao = new Notification.Builder(context)
                    .setContentTitle("Nova coordenadas recebidas")
                    .setContentText(conteudoMensagem)
                    .setContentIntent(pending)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.icone_notificacao);//Imagem png de fundo transparente e desenho na cor branca

            //Acessar o serviço de notificacao do Android
            NotificationManager servico = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            //Pedir para o serviço de notificação exibir a nossa notificação
            servico.notify(230,notificacao.build());
            //o codigo "230" pode ser qualquer valor inteiro, serve somente para
            //identificar a nossa notificação dentro do serviço do Android

        }else{
            Toast.makeText(context, "Erro! Erro! Erro!", Toast.LENGTH_LONG).show();
        }


        //Exibindo as informações em um Toast
        Toast.makeText(context, "MSG: "+ conteudoSMS + "Número: " + numeroRemetente, Toast.LENGTH_LONG).show();

    }
}
