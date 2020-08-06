package com.example.quanlydiemsinhvien.data_models;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class DanhSachSinhVien {
    private String MaSV;
    private String TenSV;
    private double Diem;
    private int lanHoc;

    public DanhSachSinhVien() {

    }

    public void setDiem(double diem) {
        Diem = diem;
    }

    public int getLanHoc() {
        return lanHoc;
    }

    public void setLanHoc(int lanHoc) {
        this.lanHoc = lanHoc;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }

    public DanhSachSinhVien(String maSV, String tenSV, double diem, int lanHoc) {
        MaSV = maSV;
        TenSV = tenSV;
        Diem = diem;
        this.lanHoc = lanHoc;
    }

    @NonNull
    @Override
    public String toString() {
        return "DanhSachSinhVien{" +
                "maSV='" + MaSV + '\'' +
                ", tenSV='" + TenSV + '\'' +
                ", diemSV='" + Diem + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("diem", getDiem());
        result.put("lanHoc", getLanHoc());
        return result;
    }
}
