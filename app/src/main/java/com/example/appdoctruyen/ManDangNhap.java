package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.DatabaseDoctruyen;

public class ManDangNhap extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;

    DatabaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);

        edtTaiKhoan = findViewById(R.id.edt_tk);
        edtMatKhau = findViewById(R.id.edt_mk);
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        btnDangKy = findViewById(R.id.btn_dangky);


        databaseDoctruyen = new DatabaseDoctruyen(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManDangNhap.this, ManDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTK = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();


                Cursor cursor = databaseDoctruyen.getData();
                while (cursor.moveToNext()){
                    String dataTenTk = cursor.getString(1);
                    String dataMK = cursor.getString(2);
                    if(dataTenTk.equals(tenTK) && dataMK.equals(matKhau)){
                        int phanquyen = cursor.getInt(4);
                        int idTK = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(ManDangNhap.this, MainActivity.class);
                        intent.putExtra("phanquyen", phanquyen);
                        intent.putExtra("idTK", idTK);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan", tentk);
                        startActivity(intent);
                    }
//                    else {
//                        Toast.makeText(ManDangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
//                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });

    }
}