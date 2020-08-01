package com.example.quanlydiemsinhvien.data_models;

public class NganhModel {
    String maKhoa;
    String maNganh;
    String tenNganh;

    public NganhModel() {
    }

    public NganhModel(String maKhoa, String maNganh, String tenNganh) {
        this.maKhoa = maKhoa;
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public String toString(){
        return tenNganh;
    }
}
