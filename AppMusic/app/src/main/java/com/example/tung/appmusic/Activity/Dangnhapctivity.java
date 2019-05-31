package com.example.tung.appmusic.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tung.appmusic.Model.Account;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dangnhapctivity extends AppCompatActivity {

    Button btdangky, btdangnhap;
    EditText edtTk, edtMk;
    String Taikhoan;
    String Matkhau;
    private  static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapctivity);
        anhxa();


        fragmentManager = getSupportFragmentManager();
        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dangnhapctivity.this,DangkyActivity.class);
                startActivity(intent);
            }
        });



        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Taikhoan = edtTk.getText().toString();
                Matkhau = edtMk.getText().toString();

                if( Taikhoan.length()>0 &&  Matkhau.length()>0) {
                    final Dataservice dataservice = APIService.getService();
                    Call<List<Account>> callback = dataservice.Logindata(Taikhoan,Matkhau);
                    callback.enqueue(new Callback<List<Account>>() {
                        @Override
                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                            ArrayList<Account> mangaccount = (ArrayList<Account>) response.body();
                            if(mangaccount.size()>0){
                                Intent intent = new Intent(Dangnhapctivity.this,ThongTinActivity.class);
                                intent.putExtra("mangaccount",mangaccount);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Account>> call, Throwable t) {
                            Toast.makeText(Dangnhapctivity.this, "sai nhé!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    final Dataservice dataservice = APIService.getService();
//                    Call<List<Account>> callback = dataservice.Logindata(Taikhoan,Matkhau);
//                    callback.enqueue(new Callback<List<Account>>() {
//                        @Override
//                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
//                            ArrayList<Account> mangaccount = (ArrayList<Account>) response.body();
//                            if(mangaccount.size()>0) {
//                                Intent intent = new Intent(Dangnhapctivity.this, MainActivity.class);
//                                intent.putExtra("mangaccount",mangaccount);
//                                startActivity(intent);
//                               // Log.d("bbb",mangaccount.get(0).getName());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Account>> call, Throwable t) {
//                            Toast.makeText(Dangnhapctivity.this, "sai", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
            }
        });
    }
    private  void  anhxa(){
        btdangnhap = findViewById(R.id.btDangnhap);
        btdangky = findViewById(R.id.btDangky);
        edtTk = findViewById(R.id.taikhoan);
        edtMk = findViewById(R.id.matkhau);
    }
}
