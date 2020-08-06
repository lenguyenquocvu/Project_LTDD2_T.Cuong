package com.example.quanlydiemsinhvien.data_models;

import java.io.Serializable;

public class SinhVien implements Serializable {
    String maSV;
    String tenSV;
    String hoSV;
    String ngaySinh;
    String sdt;
    String diaChi;
    String email;
    String maNganh;
    String maKH;

    public SinhVien() {
    }


    public SinhVien(String maSV, String tenSV, String hoSV, String ngaySinh, String sdt, String diaChi, String email, String maNganh, String maKH) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.hoSV = hoSV;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
        this.maNganh = maNganh;
        this.maKH = maKH;

    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getHoSV() {
        return hoSV;
    }

    public void setHoSV(String hoSV) {
        this.hoSV = hoSV;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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


    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

}
