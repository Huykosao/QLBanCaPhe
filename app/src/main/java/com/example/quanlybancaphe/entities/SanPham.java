package com.example.quanlybancaphe.entities;

public class SanPham {
    private int id;
    private String tenSP;
    private double gia;
    private boolean trangThai;
    public SanPham(int id, String tenSP, double gia, boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.gia = gia;
        this.trangThai = trangThai;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenSP() {
        return tenSP;
    }
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    public double getGia() {
        return gia;
    }
    public void setGia(double gia) {
        this.gia = gia;
    }
    public boolean isTrangThai() {
        return trangThai;
    }
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
