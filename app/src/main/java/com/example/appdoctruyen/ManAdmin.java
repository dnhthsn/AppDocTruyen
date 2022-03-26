package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.database.DatabaseDoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManAdmin extends AppCompatActivity implements AdapterTruyen.onListener{

    RecyclerView recThemTruyen;
    Button btnThem;

    ArrayList<Truyen> truyenArrayList;
    AdapterTruyen adapterTruyen;

    DatabaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);

        recThemTruyen = findViewById(R.id.rec_ThemTruyen);
        btnThem = findViewById(R.id.btn_themTruyen);

        initList();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);
                Intent intent = new Intent(ManAdmin.this, ManDangBai.class);
                intent.putExtra("Id", id);
                startActivity(intent);

            }
        });
    }

    private void DialoDelete(int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdelete);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btn_yes);
        Button btnNo = dialog.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtruyen = truyenArrayList.get(position).getID();
                databaseDoctruyen.Delete(idtruyen);
                Intent intent = new Intent(ManAdmin.this, ManAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(ManAdmin.this, "Xoá truyện thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void initList() {
        truyenArrayList = new ArrayList<>();

        databaseDoctruyen = new DatabaseDoctruyen(this);

        Cursor cursor1 = databaseDoctruyen.getData2();

        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            truyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recThemTruyen.setLayoutManager(layoutManager);
            adapterTruyen = new AdapterTruyen(this, truyenArrayList);
            adapterTruyen.setOnListener(this);
            recThemTruyen.setAdapter(adapterTruyen);
        }

        cursor1.moveToFirst();
        cursor1.close();
    }

    @Override
    public void OnClickItem(View view, int position) {
        DialoDelete(position);
    }
}