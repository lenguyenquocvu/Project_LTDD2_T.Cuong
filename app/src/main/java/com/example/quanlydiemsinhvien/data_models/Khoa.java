package com.example.quanlydiemsinhvien.data_models;

public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private String ngayThanhLap;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, String ngayThanhLap) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(String ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }
}
