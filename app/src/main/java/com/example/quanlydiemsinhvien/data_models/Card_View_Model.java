package com.example.quanlydiemsinhvien.data_models;

public class Card_View_Model {
    private String MaLHP;
    private String TenLHP;

    public String getMaLHP() {
        return MaLHP;
    }

    public void setMaLHP(String maLHP) {
        MaLHP = maLHP;
    }

    public String getTenLHP() {
        return TenLHP;
    }

    public void setTenLHP(String tenLHP) {
        TenLHP = tenLHP;
    }

    public Card_View_Model(String maLHP, String tenLHP) {
        MaLHP = maLHP;
        TenLHP = tenLHP;
    }
}

