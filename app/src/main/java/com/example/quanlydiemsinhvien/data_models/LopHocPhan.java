package com.example.quanlydiemsinhvien.data_models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LopHocPhan implements Serializable {
    private String maLHP;
    private String TenLHP;
    private String maGV;
    private String maMH;
    private String maNH;
    private String maHK;
    private boolean KetThuc;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLHP, String tenLHP, String maGV, String maMH, String maNH, String maHK, boolean ketThuc) {
        this.maLHP = maLHP;
        TenLHP = tenLHP;
        this.maGV = maGV;
        this.maMH = maMH;
        this.maNH = maNH;
        this.maHK = maHK;
        KetThuc = ketThuc;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public String getTenLHP() {
        return TenLHP;
    }

    public void setTenLHP(String tenLHP) {
        TenLHP = tenLHP;
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

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getMaHK() {
        return maHK;
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public boolean isKetThuc() {
        return KetThuc;
    }

    public void setKetThuc(boolean ketThuc) {
        KetThuc = ketThuc;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("maLHP", getMaLHP());
        result.put("TenLHP", getTenLHP());
        result.put("maGV", getMaGV());
        result.put("maMH", getMaMH());
        result.put("maNH", getMaNH());
        result.put("maHK", getMaHK());
        result.put("KetThuc", isKetThuc());
        return result;
    }
}
