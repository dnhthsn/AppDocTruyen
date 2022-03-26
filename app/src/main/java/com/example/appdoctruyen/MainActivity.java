package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.adapter.AdapterChuyenmuc;
import com.example.appdoctruyen.adapter.AdapterThongtin;
import com.example.appdoctruyen.adapter.AdapterTruyen;
import com.example.appdoctruyen.database.DatabaseDoctruyen;
import com.example.appdoctruyen.model.ChuyenMuc;
import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterTruyen.onListener, AdapterChuyenmuc.OnListenChuyenmuc{

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    RecyclerView recNew, recThongtin, recMain;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    AdapterTruyen adapter;
    List<Truyen> truyenList;

    ArrayList<ChuyenMuc> chuyenMucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

    DatabaseDoctruyen databaseDoctruyen;

    AdapterThongtin adapterThongtin;
    AdapterChuyenmuc adapterChuyenmuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_Main);
        viewFlipper = findViewById(R.id.viewFlipper1);
        navigationView = findViewById(R.id.navigationView1);
        recNew = findViewById(R.id.rec_new);
        recThongtin = findViewById(R.id.rec_thongtin);
        recMain = findViewById(R.id.rec_manhinhchinh);
        drawerLayout = findViewById(R.id.drawyerlayout1);
        databaseDoctruyen = new DatabaseDoctruyen(this);

        truyenList = new ArrayList<>();
        Cursor cursor1 = databaseDoctruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tenTruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            truyenList.add(new Truyen(id, tenTruyen, noidung, anh, id_tk));
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recNew.setLayoutManager(layoutManager);
        adapter = new AdapterTruyen(this, truyenList);
        adapter.setOnListener(this);
        recNew.setAdapter(adapter);
        cursor1.moveToFirst();
        cursor1.close();


        //Thông tin
        Intent intentTaikhoan = getIntent();
        email = intentTaikhoan.getStringExtra("email");
        tentaikhoan = intentTaikhoan.getStringExtra("tentaikhoan");
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan, email));
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recThongtin.setLayoutManager(layoutManager1);
        adapterThongtin = new AdapterThongtin(this, taiKhoanArrayList);
        recThongtin.setAdapter(adapterThongtin);


        //Chuyên mục
        chuyenMucArrayList = new ArrayList<>();
        chuyenMucArrayList.add(new ChuyenMuc("Đăng bài", R.drawable.ic_baseline_post_add_24));
        chuyenMucArrayList.add(new ChuyenMuc("Thông tin", R.drawable.ic_baseline_face_24));
        chuyenMucArrayList.add(new ChuyenMuc("Đăng xuất", R.drawable.ic_baseline_login_24));
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recMain.setLayoutManager(layoutManager2);
        adapterChuyenmuc = new AdapterChuyenmuc(this, chuyenMucArrayList);
        adapterChuyenmuc.setOnListenChuyenmuc(this);
        recMain.setAdapter(adapterChuyenmuc);





        ActionBar();
        ActionViewFlipper();
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void ActionViewFlipper() {
        ArrayList<String> listQuangcao = new ArrayList<>();
        listQuangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        listQuangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        listQuangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        listQuangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

        for(int i=0;i<listQuangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(listQuangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this, ManTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClickItem(View view, int position) {
        Intent intent = new Intent(MainActivity.this, ManNoiDung.class);
        String tentr = truyenList.get(position).getTenTruyen();
        String noidung = truyenList.get(position).getNoiDung();
        intent.putExtra("tentruyen", tentr);
        intent.putExtra("noidung", noidung);
        startActivity(intent);
    }

    @Override
    public void OnClickChuyenMuc(View view, int position) {
        Intent intentPhanquyen = getIntent();
        int i = intentPhanquyen.getIntExtra("phanquyen", 0);
        int idd = intentPhanquyen.getIntExtra("idd", 0);

        if(position == 0){
            if(i == 2){
                Intent intent = new Intent(MainActivity.this, ManAdmin.class);
                intent.putExtra("Id", idd);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
            }
        }else  if(position == 1){
            Intent intent = new Intent(MainActivity.this, ManThongTin.class);
            startActivity(intent);
        } else if(position == 2){
            finish();
        }
    }
}