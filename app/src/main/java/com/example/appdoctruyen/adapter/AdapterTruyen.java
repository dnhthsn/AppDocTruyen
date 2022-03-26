package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterTruyen extends RecyclerView.Adapter<AdapterTruyen.ItemviewHolder> {
    Context context;
    List<Truyen> truyenList;
    public static onListener onListener;

    public void setOnListener(AdapterTruyen.onListener onListener) {
        AdapterTruyen.onListener = onListener;
    }

    public AdapterTruyen(Context context, List<Truyen> truyenList) {
        this.context = context;
        this.truyenList = truyenList;
    }

    @NonNull
    @Override
    public ItemviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemviewHolder(LayoutInflater.from(context).inflate(R.layout.newtruyen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemviewHolder holder, int position) {
        Truyen truyen = (Truyen) getItem(position);
        holder.txtTenTruyen.setText(truyenList.get(position).getTenTruyen());
        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(
                holder.imgTruyen
        );

    }

    public Truyen getItem(int position){
        return truyenList.get(position);
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        truyenList = filteredList;
        notifyDataSetChanged();
    }

    public class ItemviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtTenTruyen;
        ImageView imgTruyen;

        public ItemviewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTruyen = itemView.findViewById(R.id.txt_tentruyenmoi);
            imgTruyen = itemView.findViewById(R.id.img_truyenmoi);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onListener != null) onListener.OnClickItem(view, getAdapterPosition());
        }
    }

    public interface onListener{
        void OnClickItem(View view, int position);
    }
}
