package com.example.quanlydiemsinhvien.data_models;

public class LopHocPhan {
    private String maLop;
    private String tenLop;
    private String maGV;
    private String maMH;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLop, String tenLop, String maGV, String maMH) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maGV = maGV;
        this.maMH = maMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    @Override
    public String toString() {
        return "LopHocPhan{" +
                "maLop='" + maLop + '\'' +
                ", tenLop='" + tenLop + '\'' +
                ", maGV='" + maGV + '\'' +
                '}';
    }
}
