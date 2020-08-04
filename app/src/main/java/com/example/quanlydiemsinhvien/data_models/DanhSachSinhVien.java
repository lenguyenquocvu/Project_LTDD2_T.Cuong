package com.example.quanlydiemsinhvien.data_models;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class DanhSachSinhVien {
    private String MaSV;
    private String TenSV;
    private int Diem;

    public DanhSachSinhVien() {

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

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }

    public DanhSachSinhVien(String maSV, String tenSV, int diem) {
        MaSV = maSV;
        TenSV = tenSV;
        Diem = diem;
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
        result.put("maSV", MaSV);
        result.put("tenSV", TenSV);
        result.put("diemSV", Diem);
        return result;
    }
}
