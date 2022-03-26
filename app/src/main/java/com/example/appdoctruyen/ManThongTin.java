package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ManThongTin extends AppCompatActivity {
    TextView txtThongtinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_thong_tin);
        txtThongtinapp = findViewById(R.id.txt_thongtin);

        String thongtin = "Ứng dụng được phát hành bởi 'Đinh Thái Sơn'\n" +
                "dinhthaison2000@gmail.com";

        txtThongtinapp.setText(thongtin);
    }
}