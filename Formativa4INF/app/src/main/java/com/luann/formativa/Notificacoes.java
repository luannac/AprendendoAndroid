package com.luann.formativa;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notificacoes {
    private Context context;
    private int CODIGO_NOTIFICACAO = 613;
    private int CODIGO_NOTIFICACAO_RESPOSTA = 445;
    NotificationManager servico = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

    public Notificacoes(Context context){
        super();
        this.context =context;
    }

    public void notificaoRecebimento(){
        //Para criar os botões abaixo da notificação é necessário adicionar
        //uma Intent, transformá-la em PendingIntent e também acionar um
        //BroadcastReceiver ou Service quando for clicado sobre um dos botões
        //Nesse exemplo, ao clicar sobre um dos três botões abaixo da notificação
        //será acionado um BroadcastReceiver (BroadcastNotificacao.java)

        Intent itBotaoSim = new Intent();
        //Configurar um ação (Action) para que seja possível identificar
        //que o botão "Sim" foi acionado
        itBotaoSim.setAction("CLICOU_BOTAO_SIM");
        //Passagem de um valor extra junto com a Intent para que seja possível acessar essa
        //notificação no BroadcastNotificacao
        itBotaoSim.putExtra("CODIGO_NOTIFICACAO", CODIGO_NOTIFICACAO);

        //Transformar em PendingIntent
        //É necessário usar o FLAG_UPDATE_CURRENT quanto se tem uma ou mais Action
        PendingIntent pendingBotaoSim = PendingIntent.getBroadcast
                (MainActivity.this, 0, itBotaoSim, PendingIntent.FLAG_UPDATE_CURRENT);

        //Para configurar a Action é necessário passar uma imagem (somente visível até a versão 24 - 7.0),
        //um texto e uma PendingIntent que, nesse caso, chama o BroadcastNotificao com a Action "CLICOU_BOTAO_SIM"
        notificacao.addAction(R.drawable.action_sim, "Sim", pendingBotaoSim);

        //Nova Action - Botão "Não"
        //Fazer o mesmo procedimento para adiconar uma Action bom o botão "Não" e um "Talvez"
        Intent itBotaoNao = new Intent();
        itBotaoNao.setAction("CLICOU_BOTAO_NAO");
        itBotaoNao.putExtra("CODIGO_NOTIFICACAO", CODIGO_NOTIFICACAO);
        PendingIntent pendingBotaoNao = PendingIntent.getBroadcast
                (MainActivity.this, 0, itBotaoNao, PendingIntent.FLAG_UPDATE_CURRENT);
        notificacao.addAction(R.drawable.action_nao, "Não", pendingBotaoNao);

        //Nova Action - "Talvez"
        Intent itBotaoTalvez = new Intent();
        itBotaoTalvez.setAction("CLICOU_BOTAO_TALVEZ");
        itBotaoTalvez.putExtra("CODIGO_NOTIFICACAO", CODIGO_NOTIFICACAO);
        PendingIntent pendingBotaoTalvez = PendingIntent.getBroadcast
                (MainActivity.this, 0, itBotaoTalvez, PendingIntent.FLAG_UPDATE_CURRENT);
        notificacao.addAction(R.drawable.action_talvez, "Talvez", pendingBotaoTalvez);

        //Exibir a notificação
        servico.notify(CODIGO_NOTIFICACAO, notificacao.build());
    }
}
