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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Albumhot> mangalbum;

    public AlbumAdapter(Context context, ArrayList<Albumhot> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    // gắn layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    //gán giá trị
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Albumhot albumhot = mangalbum.get(position);
        holder.txttencasialbum.setText(albumhot.getTenAlbum());
        holder.txttenalbum.setText(albumhot.getTenAlbum());
        Picasso.with(context).load(albumhot.getHinhAlbum()).into(holder.imghinhanhalbum);
    }

    // trả fuction, viết item
    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imghinhanhalbum;
        TextView txttenalbum,txttencasialbum;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhanhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewTenalbum);
            txttencasialbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
