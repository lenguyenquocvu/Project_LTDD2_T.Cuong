package com.example.quanlydiemsinhvien.data_models;

public class Accounts {
    private String id;
    private  String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
