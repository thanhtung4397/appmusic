package com.example.tung.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tung.appmusic.Activity.DanhsachbaihatActivity;
import com.example.tung.appmusic.Model.Albumhot;
import com.example.tung.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TatcaAlbumAdpter  extends RecyclerView.Adapter<TatcaAlbumAdpter.ViewHolder> {

    Context context;
    ArrayList<Albumhot> albumhotArrayList;

    public TatcaAlbumAdpter(Context context, ArrayList<Albumhot> albumhotArrayList) {
        this.context = context;
        this.albumhotArrayList = albumhotArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_tat_ca_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Albumhot albumhot = albumhotArrayList.get(position);
        Picasso.with(context).load(albumhot.getHinhAlbum()).into(holder.imgtatcaalbum);
        holder.txttentatcaalbum.setText(albumhot.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumhotArrayList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgtatcaalbum;
        TextView txttentatcaalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtatcaalbum = itemView.findViewById(R.id.imageviewtatcaalbum);
            txttentatcaalbum = itemView.findViewById(R.id.textviewtentatcaalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent    = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum",albumhotArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
