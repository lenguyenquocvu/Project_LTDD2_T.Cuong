package com.example.quanlydiemsinhvien.data_models;

public class KhoaHoc {
    private String maKH;
    private String batDau;
    private String ketThuc;

    public KhoaHoc() {
    }

    public KhoaHoc(String maKH, String batDau, String ketThuc) {
        this.maKH = maKH;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
    }

    public String getThoiGianKhoaHoc(){
        return this.batDau + "-" + this.ketThuc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getBatDau() {
        return batDau;
    }

    public void setBatDau(String batDau) {
        this.batDau = batDau;
    }

    public String getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(String ketThuc) {
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
