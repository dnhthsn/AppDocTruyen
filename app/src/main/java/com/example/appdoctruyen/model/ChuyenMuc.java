package com.example.appdoctruyen.model;

public class ChuyenMuc {
    private String tenChuyenmuc;
    private int imgChuyenmuc;

    public ChuyenMuc(String tenChuyenmuc, int imgChuyenmuc) {
        this.tenChuyenmuc = tenChuyenmuc;
        this.imgChuyenmuc = imgChuyenmuc;
    }

    public String getTenChuyenmuc() {
        return tenChuyenmuc;
    }

    public void setTenChuyenmuc(String tenChuyenmuc) {
        this.tenChuyenmuc = tenChuyenmuc;
    }

    public int getImgChuyenmuc() {
        return imgChuyenmuc;
    }

    public void setImgChuyenmuc(int imgChuyenmuc) {
        this.imgChuyenmuc = imgChuyenmuc;
    }
}
