package com.example.tung.appmusic.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.example.tung.appmusic.Adapter.SearchDongnhavAdapter;
import com.example.tung.appmusic.Model.Account;
import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinActivity extends AppCompatActivity {

    Button btnThoat,btnDangxuat;
    TextView txtTTten,txtTTdongnhac,txtKhongco;

    RecyclerView recyclerViewDongnhac;
    SearchDongnhavAdapter searchDongnhavAdapter;
    Toolbar toolbar;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        initPreferences();

        txtTTten = findViewById(R.id.txtTTten);
        txtTTdongnhac = findViewById(R.id.txtTTdongnhac);
        btnThoat = findViewById(R.id.btn_thoat);
        btnDangxuat = findViewById(R.id.btn_dangxuat);
        txtKhongco = findViewById(R.id.textviewchuacapnhat);
        recyclerViewDongnhac = findViewById(R.id.recyclerViewDongnhac);
        toolbar = findViewById(R.id.toolbarsearchdongnhac);

        init();


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnDangxuat){
                    editor.clear();
                    editor.commit();
                    System.exit(0);
                    finish();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dongnhac_view,menu);
        MenuItem menuItem = menu.findItem(R.id.search_dongnhac);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        String str = getIntent().getStringExtra("dongnhac");
        searchView.setQuery(str,true);
        searchView.setEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTukhoadongnhac(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              //  SearchTukhoadongnhac(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private  void  init() {
        Intent intent = getIntent();
        ArrayList<Account> accountArrayList = intent.getParcelableArrayListExtra("mangaccount");
            txtTTten.setText(accountArrayList.get(0).getName());
            txtTTdongnhac.setText(accountArrayList.get(0).getIdDongnhac());
            toolbar.setTitle(accountArrayList.get(0).getIdDongnhac());
            setSupportActionBar(toolbar);
    }

    private  void SearchTukhoadongnhac(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<Baihatduocthich>> callback = dataservice.GetSearchDongnhac(query);
        callback.enqueue(new Callback<List<Baihatduocthich>>() {
            @Override
            public void onResponse(Call<List<Baihatduocthich>> call, Response<List<Baihatduocthich>> response) {
                ArrayList<Baihatduocthich> mangbaihat  = (ArrayList<Baihatduocthich>) response.body();
                if(mangbaihat.size() > 0){
                    searchDongnhavAdapter = new SearchDongnhavAdapter(getApplication(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
                    recyclerViewDongnhac.setLayoutManager(linearLayoutManager);
                    txtKhongco.setVisibility(View.GONE);
                    recyclerViewDongnhac.setAdapter(searchDongnhavAdapter);
                }else {
                    recyclerViewDongnhac.setVisibility(View.GONE);
                    txtKhongco.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihatduocthich>> call, Throwable t) {

            }
        });
    }
}
