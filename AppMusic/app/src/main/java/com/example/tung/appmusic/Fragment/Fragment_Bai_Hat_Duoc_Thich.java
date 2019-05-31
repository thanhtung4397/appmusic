package com.example.tung.appmusic.Fragment;

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

import com.example.tung.appmusic.Adapter.BaihatduocthichAdapter;
import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Duoc_Thich extends Fragment {

    View view;
    RecyclerView recyclerViewbaihatduocthich;
    BaihatduocthichAdapter baihatduocthichAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_duoc_thich,container,false);
        recyclerViewbaihatduocthich = view.findViewById(R.id.recyclerviewbaihatduocthich);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihatduocthich>> callback = dataservice.GetBaihatduocthich();
        callback.enqueue(new Callback<List<Baihatduocthich>>() {
            @Override
            public void onResponse(Call<List<Baihatduocthich>> call, Response<List<Baihatduocthich>> response) {
                ArrayList<Baihatduocthich> baihatduocthichArrayList = (ArrayList<Baihatduocthich>) response.body();
                baihatduocthichAdapter = new BaihatduocthichAdapter(getActivity(),baihatduocthichArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihatduocthich.setLayoutManager(linearLayoutManager);
                recyclerViewbaihatduocthich.setAdapter(baihatduocthichAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihatduocthich>> call, Throwable t) {

            }
        });
    }
}
