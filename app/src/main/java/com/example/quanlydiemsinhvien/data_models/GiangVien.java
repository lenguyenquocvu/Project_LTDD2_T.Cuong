package com.example.quanlydiemsinhvien.data_models;

import java.io.Serializable;

public class GiangVien implements Serializable {
    String maGV;
    String hoGV;
    String tenGV;
    String ngaySinh;
    String sdt;
    String email;
    String maNganh;

    public GiangVien() {
    }

    public GiangVien(String maGV, String hoGV, String tenGV, String ngaySinh, String sdt, String email, String maNganh) {
        this.maGV = maGV;
        this.hoGV = hoGV;
        this.tenGV = tenGV;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.maNganh = maNganh;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoGV() {
        return hoGV;
    }

    public void setHoGV(String hoGV) {
        this.hoGV = hoGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }
}
