package com.example.tung.appmusic.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tung.appmusic.Adapter.MainViewPagerAdapter;
import com.example.tung.appmusic.Fragment.Fragment_Tim_Kiem;
import com.example.tung.appmusic.Fragment.Fragment_Trang_Chu;
import com.example.tung.appmusic.Model.Account;
import com.example.tung.appmusic.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager  viewPager;
    Button btnThongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        btnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenmanghinh();
                finish();
            }
        });
        init();
    }

    private  void chuyenmanghinh(){
        Intent intent = new Intent(this,ThongTinActivity.class);
//        ArrayList<Account> mangaccount = new ArrayList<Account>();
//        intent.putExtra("mangaccount",mangaccount);
//        intent.putExtra("dongnhac",mangaccount.get(0).getIdDongnhac());
        startActivity(intent);
    }


    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
     //   mainViewPagerAdapter.addFragment(new Fragment_Thongtin(),"Thông Tin");
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(0).setIcon(R.drawable.iconthongtin);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontk);
    }

    private void anhxa(){
        btnThongtin = findViewById(R.id.btn_Thongtin);
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
