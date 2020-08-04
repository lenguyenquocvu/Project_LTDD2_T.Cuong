package com.example.quanlydiemsinhvien.data_models;

import java.util.ArrayList;
import java.util.HashMap;

public class ChuongTrinhDaoTao {
    private KhoaHoc khoaHoc;
    private Nganh nganh;
    private ArrayList<MonHoc> dsMonHocs;
    private HashMap map = new HashMap();

    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }

    public ChuongTrinhDaoTao() {
    }

    public ChuongTrinhDaoTao(KhoaHoc khoaHoc, Nganh nganh, HashMap map) {
        this.khoaHoc = khoaHoc;
        this.nganh = nganh;
        this.map = map;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public ArrayList<MonHoc> getDsMonHocs() {
        return dsMonHocs;
    }

    public void setDsMonHocs(ArrayList<MonHoc> dsMonHocs) {
        this.dsMonHocs = dsMonHocs;
    }
}
