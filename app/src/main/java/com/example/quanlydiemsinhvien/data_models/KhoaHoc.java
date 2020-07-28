package com.example.quanlydiemsinhvien.data_models;

import java.io.Serializable;

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
}
