package com.example.quanlydiemsinhvien.data_models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KhoaHoc implements Serializable {
    private String maKH;
    private int batDau;
    private int ketThuc;


    public KhoaHoc() {
    }

    public KhoaHoc(String maKH, int batDau, int ketThuc) {
        this.maKH = maKH;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getBatDau() {
        return batDau;
    }

    public void setBatDau(int batDau) {
        this.batDau = batDau;
    }

    public int getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(int ketThuc) {
        this.ketThuc = ketThuc;
    }

    @Override
    public String toString() {
        return "KhoaHoc{" +
                "maKH='" + maKH + '\'' +
                ", batDau='" + batDau + '\'' +
                ", ketThuc='" + ketThuc + '\'' +
                '}';
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("maKH", getMaKH());
        result.put("batDau", getBatDau());
        result.put("ketThuc", getKetThuc());
        return result;

    }
}
