package com.luann.formativa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main ,parent,false);//Mudar R.layout.activity_main
        ViewHolderChaves vhChaves = new ViewHolderChaves(view);

        return vhChaves;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderChaves vhChaves = (ViewHolderChaves) holder;
        //vhChaves.
    }

    @Override
    public int getItemCount() {
        return chaves.size();
    }
}
