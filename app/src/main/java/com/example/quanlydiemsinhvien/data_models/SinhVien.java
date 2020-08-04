package com.example.quanlydiemsinhvien.data_models;

public class SinhVien {
    private String maSV;
    private String hoTenSV;
    private String maHP;
    private String tenHP;
    private String soTC;
    private String diemMH;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTenSV() {
        return hoTenSV;
    }

    public void setHoTenSV(String hoTenSV) {
        this.hoTenSV = hoTenSV;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getSoTC() {
        return soTC;
    }

    public void setSoTC(String soTC) {
        this.soTC = soTC;
    }

    public String getDiemMH() {
        return diemMH;
    }

    public void setDiemMH(String diemMH) {
        this.diemMH = diemMH;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", hoTenSV='" + hoTenSV + '\'' +
                ", maHP='" + maHP + '\'' +
                ", tenHP='" + tenHP + '\'' +
                ", soTC='" + soTC + '\'' +
                ", diemMH='" + diemMH + '\'' +
                '}';
    }
}
