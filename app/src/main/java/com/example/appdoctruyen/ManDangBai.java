package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.DatabaseDoctruyen;
import com.example.appdoctruyen.model.Truyen;

public class ManDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnDangBai;

    DatabaseDoctruyen databaseDoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);

        edtTenTruyen = findViewById(R.id.edt_Tentruyen);
        edtNoiDung = findViewById(R.id.edt_Noidung);
        edtAnh = findViewById(R.id.edt_AnhTruyen);
        btnDangBai = findViewById(R.id.btn_dangbai);

        databaseDoctruyen = new DatabaseDoctruyen(this);
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(ManDangBai.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseDoctruyen.AddTruyen(truyen);
                    Intent intent = new Intent(ManDangBai.this, ManAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    private Truyen CreateTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);

        Truyen truyen = new Truyen(tentruyen, noidung, img, id);
        return truyen;
    }
}