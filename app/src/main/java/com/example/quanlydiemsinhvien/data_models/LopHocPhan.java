package com.example.quanlydiemsinhvien.data_models;

public class LopHocPhan {
    private boolean ketThuc;
    private String maGV;
    private String maHK;
    private String maLHP;
    private String maMH;
    private String maNH;
    private String tenLHP;

    public LopHocPhan() {
    }

    public LopHocPhan(boolean ketThuc, String maGV, String maHK, String maLHP, String maMH, String maNH, String tenLHP) {
        this.ketThuc = ketThuc;
        this.maGV = maGV;
        this.maHK = maHK;
        this.maLHP = maLHP;
        this.maMH = maMH;
        this.maNH = maNH;
        this.tenLHP = tenLHP;
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

    public String getTenLHP() {
        return tenLHP;
    }

    public void setTenLHP(String tenLHP) {
        this.tenLHP = tenLHP;
    }
}
