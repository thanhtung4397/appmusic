package com.example.tung.appmusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.R;

import java.util.ArrayList;

public class LyricAdapter extends RecyclerView.Adapter<LyricAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihatduocthich> mangbaihat;

    public LyricAdapter(Context context, ArrayList<Baihatduocthich> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_lyric,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihatduocthich baihat = mangbaihat.get(i);
        viewHolder.txtlyric.setText(baihat.getLyric());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtlyric;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlyric = itemView.findViewById(R.id.textviewlyric);
        }
    }
}
