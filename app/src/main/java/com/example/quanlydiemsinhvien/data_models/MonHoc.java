package com.example.quanlydiemsinhvien.data_models;

public class MonHoc {
    private String maMH;
    private String TenMH;
    private int SoTinChi;
    private String maNganh;

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    private boolean showMenu = false;

    public MonHoc() {
    }

    public MonHoc(String maMH, String TenMH, int SoTinChi, String maNganh) {
        this.maMH = maMH;
        this.TenMH = TenMH;
        this.SoTinChi = SoTinChi;
        this.maNganh = maNganh;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        this.TenMH = tenMH;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.SoTinChi = soTinChi;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public String getMaKH() {
        return maMH;
    }

    public void setMaKH(String maKH) {
        this.maMH = maKH;
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMH='" + maMH + '\'' +
                ", tenMH='" + maMH + '\'' +
                ", soTinChi=" + SoTinChi +
                '}';
    }
}
