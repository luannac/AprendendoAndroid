package com.luann.formativa;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderChaves extends RecyclerView.ViewHolder {
    TextView chave,autenticacao,dataHora;
    ImageView status;
    Constraints constraints;

    public ViewHolderChaves(@NonNull View itemView) {
        super(itemView);
        chave = itemView.findViewById(R.id.cardChave_campo_chave);
        autenticacao = itemView.findViewById(R.id.cardChave_campo_autenticacao);
        dataHora = itemView.findViewById(R.id.cardChaves_campo_data);
        status = itemView.findViewById(R.id.cardChaves_imagestatus);
        constraints = itemView.findViewById(R.id.cardChaves_constraint);
    }
}
