package com.example.quanlydiemsinhvien.data_models;

import java.io.Serializable;

public class SinhVienModel implements Serializable {
    String maSV;
    String TenSV;
    String HoSV;
    String NgaySinh;
    String SDT;
    String DiaChi;
    String Email;
    String maNganh;

    public SinhVienModel() {
    }

    public SinhVienModel(String maSV, String tenSV, String hoSV, String ngaySinh, String SDT, String diaChi, String email, String maNganh) {
        this.maSV = maSV;
        TenSV = tenSV;
        HoSV = hoSV;
        NgaySinh = ngaySinh;
        this.SDT = SDT;
        DiaChi = diaChi;
        Email = email;
        this.maNganh = maNganh;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getHoSV() {
        return HoSV;
    }

    public void setHoSV(String hoSV) {
        HoSV = hoSV;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
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
