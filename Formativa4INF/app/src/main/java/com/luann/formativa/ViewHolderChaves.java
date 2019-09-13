package com.luann.formativa;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderChaves extends RecyclerView.ViewHolder {
    TextView chave,autenticacao,dataHora;
    ImageView status;

    public ViewHolderChaves(@NonNull View itemView) {
        super(itemView);
        /*chave = itemView.findViewById(R.id.);
        autenticacao = itemView.findViewById(R.id.);
        dataHora = itemView.findViewById(R.id.);
        status = itemView.findViewById(R.id.);*/
    }
}
