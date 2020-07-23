package com.example.quanlydiemsinhvien.data_models;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhSachSinhVien {
    private String MaSV;
    private String TenSV;
    private int Diem;

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
}
