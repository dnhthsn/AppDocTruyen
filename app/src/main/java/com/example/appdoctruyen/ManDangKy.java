package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.DatabaseDoctruyen;
import com.example.appdoctruyen.model.TaiKhoan;

public class ManDangKy extends AppCompatActivity {
    EditText edttaikhoanDK, edtMatkhauDK, edtEmailDK;
    Button btnDangky, btnHuy;

    DatabaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);
        databaseDoctruyen = new DatabaseDoctruyen(this);

        edttaikhoanDK = findViewById(R.id.edt_taikhoanDK);
        edtMatkhauDK = findViewById(R.id.edt_matkhauDK);
        edtEmailDK = findViewById(R.id.edt_emailDK);

        btnDangky = findViewById(R.id.btn_confirmDangky);
        btnHuy = findViewById(R.id.btn_huy);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edttaikhoanDK.getText().toString();
                String matkhau = edtMatkhauDK.getText().toString();
                String email = edtEmailDK.getText().toString();

                TaiKhoan tk1 = CreateTaiKhoan();
                if (taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Toast.makeText(ManDangKy.this, "BẠN ĐIỀN THIẾU THÔNG TIN", Toast.LENGTH_SHORT).show();

                }else {
                    databaseDoctruyen.AddTaiKhoan(tk1);
                    Toast.makeText(ManDangKy.this, "ĐĂNG KÝ THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManDangKy.this, ManDangNhap.class);
                    startActivity(intent);
                }
            }
        });
    }

    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edttaikhoanDK.getText().toString();
        String matkhau = edtMatkhauDK.getText().toString();
        String email = edtEmailDK.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, email, phanquyen);

        return tk;

    }

}