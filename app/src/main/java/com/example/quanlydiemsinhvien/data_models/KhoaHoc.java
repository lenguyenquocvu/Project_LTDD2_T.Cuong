package com.example.quanlydiemsinhvien.data_models;

public class KhoaHoc {
    private Long batDau;
    private Long ketThuc;
    private String maKH;

    public KhoaHoc() {
    }

    public KhoaHoc(Long batDau, Long ketThuc, String maKH) {
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.maKH = maKH;
    }

    public Long getBatDau() {
        return batDau;
    }

    public void setBatDau(Long batDau) {
        this.batDau = batDau;
    }

    public Long getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Long ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
}
