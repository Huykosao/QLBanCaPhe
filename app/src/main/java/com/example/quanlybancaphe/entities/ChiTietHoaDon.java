package com.example.quanlybancaphe.entities;

import java.text.DecimalFormat;

public class ChiTietHoaDon {
    private String tenSP;
    private int soLuong;
    private double gia;

    public ChiTietHoaDon(String tenSP, int soLuong, double gia) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;

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
        DecimalFormat dcf = new DecimalFormat("#,###");
        double thanhTien = soLuong * gia;
        return tenSP + "\n" +
                "Số lượng: " + soLuong + " x " + dcf.format(gia) + " = " + dcf.format(thanhTien) + " VND";
    }
}
