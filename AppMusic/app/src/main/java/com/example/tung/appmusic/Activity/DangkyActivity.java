package com.example.tung.appmusic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tung.appmusic.Model.Account;
import com.example.tung.appmusic.R;
import com.example.tung.appmusic.Service.APIService;
import com.example.tung.appmusic.Service.Dataservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tung.appmusic.Service.APIService.base_url;

public class DangkyActivity extends AppCompatActivity {

    Button btxacnhan, bthuy;
    EditText edttenTK, edtMK,editemail,edtDongnhac;
    RadioGroup groupnhac;
    RadioButton radioButton,rbPop,rbBallad,rbBolero,rbKhac;

    String Taikhoan;
    String Matkhau;
    String Email;
    String Dongnhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        anhxa();
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangkyActivity.this,Dangnhapctivity.class);
                startActivity(intent);
            }
        });

        btxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Taikhoan = edttenTK.getText().toString();
                Matkhau = edtMK.getText().toString();
                Email = editemail.getText().toString();
           //     Dongnhac = edtDongnhac.getText().toString();
                Dongnhac = rbPop.getText().toString();
                Dongnhac = rbBallad.getText().toString();
                Dongnhac = rbBolero.getText().toString();
                Dongnhac = rbKhac.getText().toString();
                Dongnhac = radioButton.getText().toString();


                if( Taikhoan.length()>0 && Email.length()>0 && Matkhau.length()>0)
                {
                    final Dataservice dataservice = APIService.getService();
                    Call<String> callback = (Call<String>) dataservice.InsertData(Taikhoan,Email,Matkhau,Dongnhac);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String reseul = response.body();
                            if( reseul.equals("success"));{
                                Toast.makeText(DangkyActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(DangkyActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
    //    edtDongnhac = findViewById(R.id.edittextDongnhac);
        btxacnhan = findViewById(R.id.btnXacnhan);
        bthuy = findViewById(R.id.btnHuy);
        edttenTK = findViewById(R.id.edittexttentaikhoan);
        edtMK = findViewById(R.id.edittextmatkhau);
        editemail = findViewById(R.id.edittextemail);

        groupnhac = (RadioGroup) findViewById(R.id.GroupDongnhac);

        rbPop = findViewById(R.id.radiobtPop);
        rbBallad = findViewById(R.id.radiobtBallad);
        rbBolero = findViewById(R.id.radiobtBolero);
        rbKhac = findViewById(R.id.radiobtKhac);
        groupnhac.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton =(RadioButton) findViewById(i);
                switch (i)
                {
                    case R.id.radiobtPop:

                        break;
                    case R.id.radiobtBallad:

                        break;
                    case  R.id.radiobtBolero:

                        break;
                    case  R.id.radiobtKhac:

                        break;
                }
            }
        });

    }
}