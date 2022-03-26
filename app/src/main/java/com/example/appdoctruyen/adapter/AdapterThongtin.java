package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.ChuyenMuc;
import com.example.appdoctruyen.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class AdapterThongtin extends RecyclerView.Adapter<AdapterThongtin.ItemViewHolder> {
    Context context;
    List<TaiKhoan> taiKhoanArrayList;

    public AdapterThongtin(Context context, List<TaiKhoan> taiKhoanArrayList) {
        this.context = context;
        this.taiKhoanArrayList = taiKhoanArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.navigation_thongtin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tenTaiKhoan.setText(taiKhoanArrayList.get(position).getmTenTaiKhoan());
        holder.txtGmail.setText(taiKhoanArrayList.get(position).getmEmail());
    }

    @Override
    public int getItemCount() {
        return taiKhoanArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tenTaiKhoan, txtGmail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tenTaiKhoan = itemView.findViewById(R.id.txt_userName);
            txtGmail = itemView.findViewById(R.id.txt_gmail);
        }
    }

}
