package com.example.quanlybancaphe.Models;

public class HoaDonItem {
    private int maSP;
    private String tenSP;
    private int soLuong;
    private double gia;

    public HoaDonItem(int maSP, String tenSP, int soLuong, double gia){
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    @Override
    public String toString() {
        return "Sản phẩm: " + tenSP + " x " + soLuong;
    }
}
