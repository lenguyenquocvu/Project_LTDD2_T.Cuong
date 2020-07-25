package com.example.quanlydiemsinhvien.data_models;

public class Nganh {
    private String maNganh;
    private String tenNganh;
    private Khoa khoa;

    public Nganh() {
    }

    public Nganh(String maNganh, String tenNganh) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
    };

    public Nganh(String maNganh, String tenNganh, Khoa khoa) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.khoa = khoa;
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
}
