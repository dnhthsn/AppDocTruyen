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
import com.example.appdoctruyen.model.ChuyenMuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterChuyenmuc extends RecyclerView.Adapter<AdapterChuyenmuc.itemViewHolder> {
    Context context;
    List<ChuyenMuc> chuyenMucList;
    public static OnListenChuyenmuc onListenChuyenmuc;

    public void setOnListenChuyenmuc(OnListenChuyenmuc onListenChuyenmuc) {
        AdapterChuyenmuc.onListenChuyenmuc = onListenChuyenmuc;
    }

    public AdapterChuyenmuc(Context context, List<ChuyenMuc> chuyenMucList) {
        this.context = context;
        this.chuyenMucList = chuyenMucList;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemViewHolder(LayoutInflater.from(context).inflate(R.layout.chuyen_muc, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        ChuyenMuc cm = (ChuyenMuc) getItem(position);
//        Picasso.get().load(cm.getImgChuyenmuc()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_24).into(
//                holder.imgChuyenmuc
//        );
        holder.imgChuyenmuc.setImageResource(chuyenMucList.get(position).getImgChuyenmuc());
        holder.txtchuyenmuc.setText(chuyenMucList.get(position).getTenChuyenmuc());
    }

    public ChuyenMuc getItem(int position){
        return chuyenMucList.get(position);
    }

    @Override
    public int getItemCount() {
        return chuyenMucList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgChuyenmuc;
        TextView txtchuyenmuc;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChuyenmuc = itemView.findViewById(R.id.img_Chuyenmuc);
            txtchuyenmuc = itemView.findViewById(R.id.txt_tenchuyenmuc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onListenChuyenmuc != null) onListenChuyenmuc.OnClickChuyenMuc(view, getAdapterPosition());
        }
    }

    public interface OnListenChuyenmuc{
        void OnClickChuyenMuc(View view, int position);
    }
}
