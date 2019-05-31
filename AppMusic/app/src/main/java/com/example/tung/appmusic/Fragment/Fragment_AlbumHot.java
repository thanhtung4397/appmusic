package com.example.tung.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tung.appmusic.Activity.DanhsachtatcaalbumActivity;
import com.example.tung.appmusic.Adapter.AlbumAdapter;
import com.example.tung.appmusic.Model.Albumhot;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_AlbumHot extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtxemthemAlbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_albumhot,container,false);
        recyclerViewAlbum = view.findViewById(R.id.recycleveryAlbum);
        txtxemthemAlbum = view.findViewById(R.id.textviewxemthemAlbum);
        txtxemthemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaalbumActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Albumhot>> callback = dataservice.GetAlbumhot();
        callback.enqueue(new Callback<List<Albumhot>>() {
            @Override
            public void onResponse(Call<List<Albumhot>> call, Response<List<Albumhot>> response) {
                ArrayList<Albumhot> albumhotArrayList = (ArrayList<Albumhot>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumhotArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Albumhot>> call, Throwable t) {

            }
        });
    }
}
