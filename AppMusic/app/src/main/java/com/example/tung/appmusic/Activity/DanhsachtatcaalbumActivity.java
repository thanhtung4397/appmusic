package com.example.tung.appmusic.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.tung.appmusic.Adapter.TatcaAlbumAdpter;
import com.example.tung.appmusic.Model.Albumhot;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {

    RecyclerView recyclerViewtatcaalbum;
    Toolbar toolbartatcaalbum;
    TatcaAlbumAdpter tatcaAlbumAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Albumhot>> callback = dataservice.GettatcaAlbum();
        callback.enqueue(new Callback<List<Albumhot>>() {
            @Override
            public void onResponse(Call<List<Albumhot>> call, Response<List<Albumhot>> response) {
                ArrayList<Albumhot> mangalbum = (ArrayList<Albumhot>) response.body();
                tatcaAlbumAdpter = new TatcaAlbumAdpter(DanhsachtatcaalbumActivity.this,mangalbum);
                recyclerViewtatcaalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerViewtatcaalbum.setAdapter(tatcaAlbumAdpter);
            }

            @Override
            public void onFailure(Call<List<Albumhot>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtatcaalbum = findViewById(R.id.recyclerviewdanhsachtatcaalbum);
        toolbartatcaalbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbartatcaalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các Album");
        toolbartatcaalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
