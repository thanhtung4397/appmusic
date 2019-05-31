package com.example.tung.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tung.appmusic.Activity.PlayNhacActivity;
import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaihatAdapter extends RecyclerView.Adapter<SearchBaihatAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihatduocthich> mangbaihat;

    public SearchBaihatAdapter(Context context, ArrayList<Baihatduocthich> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihatduocthich  baihatduocthich = mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihatduocthich.getTenbaihat());
        holder.txtCasi.setText(baihatduocthich.getCasi());
        Picasso.with(context).load(baihatduocthich.getHinhbaihat()).into(holder.imgBaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class    ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenbaihat,txtCasi;
        ImageView imgBaihat,imgLuotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textviewSearchTenbaihat);
            txtCasi = itemView.findViewById(R.id.textviewSearchTencasi);
            imgBaihat = itemView.findViewById(R.id.imageviewSearchbaihat);
            imgLuotthich = itemView.findViewById(R.id.imageviewSearchLuotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotthich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")) {
                                Toast.makeText(context,"Đã Thích",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotthich.setEnabled(false);
                }
            });
        }
    }
}
