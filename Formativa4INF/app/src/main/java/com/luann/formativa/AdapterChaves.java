package com.luann.formativa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterChaves extends RecyclerView.Adapter {
    private Context context;
    private List<Chave> chaves;

    public AdapterChaves(List<Chave> chaves, Context applicationContext) {
        this.context = applicationContext;
        this.chaves = chaves;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_chaves ,parent,false);
        com.luann.formativa.ViewHolderChaves vhChaves = new com.luann.formativa.ViewHolderChaves(view);

        return vhChaves;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        com.luann.formativa.ViewHolderChaves vhChaves = (com.luann.formativa.ViewHolderChaves) holder;
        vhChaves.chave.setText(chaves.get(position).getChave());
        vhChaves.autenticacao.setText(chaves.get(position).getAutenticacao());

        Date datahora = new Date (chaves.get (position).getDatahora());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
        vhChaves.dataHora.setText(sdf.format(datahora));

        if (chaves.get(position).getStatus()==1){
            vhChaves.status.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),R.color.colorTrue,null));
            vhChaves.constraints.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),R.color.colorTrueBackground,null));
        }else{
            vhChaves.status.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),R.color.colorFalse,null));
            vhChaves.constraints.setBackgroundColor(ResourcesCompat.getColor(context.getResources(),R.color.colorFalseBackground,null));
        }

    }

    @Override
    public int getItemCount() {
        return chaves.size();
    }
}
