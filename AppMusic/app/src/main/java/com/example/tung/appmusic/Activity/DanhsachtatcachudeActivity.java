package com.example.tung.appmusic.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.example.tung.appmusic.Adapter.DanhsachtatcachudeAdapter;
import com.example.tung.appmusic.Model.ChuDe;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerViewctatcachude;
    Toolbar toolbartatcachude;
    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        retrofit2.Call<List<ChuDe>> callabck = dataservice.GetTatcachude();
        callabck.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachtatcachudeAdapter = new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this,mangchude);
                recyclerViewctatcachude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1  ));
                recyclerViewctatcachude.setAdapter(danhsachtatcachudeAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewctatcachude = findViewById(R.id.recyclerviewchude);
        toolbartatcachude = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbartatcachude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
