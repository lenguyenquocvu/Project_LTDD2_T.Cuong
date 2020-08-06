package com.example.quanlydiemsinhvien.data_models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MonHoc implements Serializable {
    private String maMH;
    private String TenMH;
    private int SoTinChi;
    private String maNganh;



    public MonHoc() {
    }

    public MonHoc(String maMH, String TenMH, int SoTinChi, String maNganh) {
        this.maMH = maMH;
        this.TenMH = TenMH;
        this.SoTinChi = SoTinChi;
        this.maNganh = maNganh;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        this.TenMH = tenMH;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.SoTinChi = soTinChi;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMH='" + maMH + '\'' +
                ", tenMH='" + maMH + '\'' +
                ", soTinChi=" + SoTinChi +
                '}';
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("maMH", getMaMH());
        result.put("tenMH", getTenMH());
        result.put("SoTinChi", getSoTinChi());
        result.put("maNganh", getMaNganh());
        return result;
    }
}
