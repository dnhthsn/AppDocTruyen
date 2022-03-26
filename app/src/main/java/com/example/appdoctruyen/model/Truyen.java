package com.example.appdoctruyen.model;

public class Truyen {
    private int ID;
    private String tenTruyen;
    private String noiDung;
    private String anh;
    private int ID_TK;

    public Truyen(String tenTruyen, String noiDung, String anh, int ID_TK) {
        this.tenTruyen = tenTruyen;
        this.noiDung = noiDung;
        this.anh = anh;
        this.ID_TK = ID_TK;
    }

    public Truyen(int ID, String tenTruyen, String noiDung, String anh, int ID_TK) {
        this.ID = ID;
        this.tenTruyen = tenTruyen;
        this.noiDung = noiDung;
        this.anh = anh;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
