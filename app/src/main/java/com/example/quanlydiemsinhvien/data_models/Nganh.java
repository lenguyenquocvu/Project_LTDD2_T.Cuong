package com.example.quanlydiemsinhvien.data_models;

import java.util.HashMap;
import java.util.Map;

public class Nganh {
    private String maKhoa;
    private String maNganh;
    private String tenNganh;

    public Nganh() {
    }

    public Nganh(String maNganh, String tenNganh) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
    };

    public Nganh(String maKhoa, String maNganh, String tenNganh) {
        this.maKhoa = maKhoa;
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
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

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    @Override
    public String toString() {
        return "Nganh{" +
                "maKhoa='" + maKhoa + '\'' +
                ", maNganh='" + maNganh + '\'' +
                ", tenNganh='" + tenNganh + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("maKhoa", maKhoa);
        result.put("maNganh", maNganh);
        result.put("tenNganh", tenNganh);

        return result;
    }
}
