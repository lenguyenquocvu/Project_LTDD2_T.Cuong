package com.example.quanlydiemsinhvien.data_models;

public class ChuongTrinhDaoTao {
    private String maMH;
    private String tenMH;
    private int soTinChi;
    private String maKH;
    private boolean showMenu = false;

    public ChuongTrinhDaoTao() {
    }

    public ChuongTrinhDaoTao(String maMH, String tenMH, int soTinChi, String maKH) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTinChi = soTinChi;
        this.maKH = maKH;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    @Override
    public String toString() {
        return "ChuongTrinhDaoTao{" +
                "maMH='" + maMH + '\'' +
                ", tenMH='" + tenMH + '\'' +
                ", soTinChi=" + soTinChi +
                '}';
    }
}
