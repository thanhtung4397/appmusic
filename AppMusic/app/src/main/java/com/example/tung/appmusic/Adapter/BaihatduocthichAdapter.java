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

public class BaihatduocthichAdapter extends RecyclerView.Adapter<BaihatduocthichAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihatduocthich> baihatduocthichArrayList;

    public BaihatduocthichAdapter(Context context, ArrayList<Baihatduocthich> baihatduocthichArrayList) {
        this.context = context;
        this.baihatduocthichArrayList = baihatduocthichArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_duoc_thich,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Baihatduocthich baihatduocthich = baihatduocthichArrayList.get(position);
        holder.txtcasi.setText(baihatduocthich.getCasi());
        holder.txtten.setText(baihatduocthich.getTenbaihat());
        Picasso.with(context).load(baihatduocthich.getHinhbaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatduocthichArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtten,txtcasi;
        ImageView imghinh,imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihatduocthich);
            txtcasi = itemView.findViewById(R.id.textviewtencasibaihatduocthich);
            imghinh =itemView.findViewById(R.id.imageviewbaihatduocthich);
            imgluotthich = itemView.findViewById(R.id.imagheviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baihatduocthichArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotthich("1",baihatduocthichArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("oke")) {
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Bị Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
