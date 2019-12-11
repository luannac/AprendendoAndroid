package com.luann.aprendendoandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCardMenu extends RecyclerView.Adapter {
    private Context context;
    private List<ModeloProjeto> projetos;

    public AdapterCardMenu(Context context, List<ModeloProjeto> projetos) {
        this.context = context;
        this.projetos = projetos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_menu_principal,parent,false);
        RecyclerView.ViewHolder viewHolder = new ViewHolderMenu(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderMenu viewHolderMenu = (ViewHolderMenu) holder;

        viewHolderMenu.getBotao().setText(projetos.get(position).nome);
        viewHolderMenu.getBotao().setOnClickListener(projetos.get(position).getIniciar());
    }

    @Override
    public int getItemCount() {
        return projetos.size();
    }
}
