package com.example.quanlydiemsinhvien.data_models;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class DanhSachLop {
    private boolean ketThuc;
    private String maGV;
    private String maHK;
    private String maLHP;
    private String tenLHP;
    private String maMH;
    private String maNH;

    public DanhSachLop() {

    }

    public boolean isKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(boolean ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaHK() {
        return maHK;
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public String getTenLHP() {
        return tenLHP;
    }

    public void setTenLHP(String tenLHP) {
        this.tenLHP = tenLHP;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public DanhSachLop(String maLHP, String tenLHP, String maGV, String maHK, String maMH, String maNH, boolean ketThuc) {
        this.ketThuc = ketThuc;
        this.maGV = maGV;
        this.maHK = maHK;
        this.maLHP = maLHP;
        this.tenLHP = tenLHP;
        this.maMH = maMH;
        this.maNH = maNH;
    }


    @NonNull
    @Override
    public String toString() {
        return "LopHocPhan{" +
                "maLHP='" + maLHP + '\'' +
                ", tenLHP='" + tenLHP + '\'' +
                ", maGV='" + maGV + '\'' +
                ", maHK='" + maHK + '\'' +
                ", maNH='" + maNH + '\'' +
                ", maMH='" + maMH + '\'' +
                ", ketThuc='" + ketThuc + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("maLHP", maLHP);
        result.put("tenLHP", tenLHP);
        result.put("maGV", maGV);
        result.put("maHK", maHK);
        result.put("maMH", maMH);
        result.put("maNH", maNH);
        result.put("ketThuc", ketThuc);

        return result;
    }
}

