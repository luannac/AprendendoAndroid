package com.luann.aprendendoandroid;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderMenu extends RecyclerView.ViewHolder {
    private ImageView img;
    private Button botao;

    public ViewHolderMenu(@NonNull View itemView) {
        super(itemView);
        this.img = itemView.findViewById(R.id.img_card_menu);
        this.botao = itemView.findViewById(R.id.button_card_menu);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Button getBotao() {
        return botao;
    }

    public void setBotao(Button botao) {
        this.botao = botao;
    }

}
