package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.database.DatabaseDoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity implements AdapterTruyen.onListener {

    RecyclerView recTK;
    EditText edtTK;

    ArrayList<Truyen> truyenArrayList;

    ArrayList<Truyen> arrayList;

    AdapterTruyen adapterTruyen;

    DatabaseDoctruyen databaseDoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        recTK = findViewById(R.id.recTimKiem);
        edtTK = findViewById(R.id.edt_tinkiem);

        initList();
        edtTK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text){
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item:truyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                arrayList.add(item);
            }
        }

        adapterTruyen.filterList(filteredList);
    }

    private void initList() {
        truyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();

        databaseDoctruyen = new DatabaseDoctruyen(this);

        Cursor cursor = databaseDoctruyen.getData2();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            truyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            arrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recTK.setLayoutManager(layoutManager);
            adapterTruyen = new AdapterTruyen(this, truyenArrayList);
            adapterTruyen.setOnListener(this);
            recTK.setAdapter(adapterTruyen);
        }

        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public void OnClickItem(View view, int position) {
        Intent intent = new Intent(ManTimKiem.this, ManNoiDung.class);
        String tent =  arrayList.get(position).getTenTruyen();
        String noidungt = arrayList.get(position).getNoiDung();
        intent.putExtra("tentruyen", tent);
        intent.putExtra("noidung", noidungt);
        startActivity(intent);

    }
}