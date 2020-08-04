package com.example.quanlydiemsinhvien.data_models;

import java.io.Serializable;

public class GiangVien implements Serializable {
    private String maGV;
    private String HoGV;
    private String TenGV;
    private String NgaySinh;
    private String SDT;
    private String Email;
    private String maNganh;

    public GiangVien() {
    }

    public GiangVien(String maGV, String hoGV, String tenGV, String ngaySinh, String SDT, String email, String maNganh) {
        this.maGV = maGV;
        HoGV = hoGV;
        TenGV = tenGV;
        NgaySinh = ngaySinh;
        this.SDT = SDT;
        Email = email;
        this.maNganh = maNganh;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoGV() {
        return HoGV;
    }

    public void setHoGV(String hoGV) {
        HoGV = hoGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }
}
