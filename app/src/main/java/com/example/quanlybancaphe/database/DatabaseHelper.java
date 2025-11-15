package com.example.quanlybancaphe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import  android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlybancaphe.entities.ChiTietHoaDon;
import com.example.quanlybancaphe.entities.HoaDon;
import com.example.quanlybancaphe.entities.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "quanlybancaphe.db";

    public static final int DATABASE_VERSION = 8;

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sanPham = "CREATE TABLE IF NOT EXISTS SanPham (" +
                "maSP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSP TEXT NOT NULL," +
                "gia REAL NOT NULL,"+
                "trangThai INTEGER NOT NULL DEFAULT 1)";
        db.execSQL(sanPham);
        String chiTietHoaDon = "CREATE TABLE IF NOT EXISTS ChiTietHoaDon (" +
                "maCTHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maHD INTEGER NOT NULL," +
                "maSP INTEGER NOT NULL," +
                "soLuong INTEGER NOT NULL," +
                "donGiaLucBan REAL NOT NULL," +
                "FOREIGN KEY (maHD) REFERENCES HoaDon(maHD)," +
                "FOREIGN KEY (maSP) REFERENCES SanPham(maSP))";
        db.execSQL(chiTietHoaDon);
        String hoaDon = "CREATE TABLE IF NOT EXISTS HoaDon (" +
                "maHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ngayLap TEXT NOT NULL," +
                "tongTien REAL NOT NULL)";
        db.execSQL(hoaDon);
        String nguoiDung = "CREATE TABLE IF NOT EXISTS NguoiDung (" +
                "tenDangNhap TEXT PRIMARY KEY," +
                "matKhau TEXT NOT NULL," +
                "hoTen TEXT NOT NULL," +
                "quyenHan TEXT NOT NULL)";
        db.execSQL(nguoiDung);
        String insertNguoiDung = "INSERT INTO NguoiDung (tenDangNhap, matKhau, hoTen, quyenHan) " +
                "VALUES ('admin', 'admin', 'admin', 'admin'), " +
                "('user', 'user', 'user', 'user')";
        db.execSQL(insertNguoiDung);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ChiTietHoaDon");
        db.execSQL("DROP TABLE IF EXISTS HoaDon");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS NguoiDung");
        onCreate(db);
    }
    //kiem tra dang nhap
    public boolean checkUser(String tk, String mk) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM NguoiDung WHERE tenDangNhap=? AND matKhau=?";
        Cursor cursor = db.rawQuery(query, new String[]{tk, mk});
        boolean result = cursor.getCount() > 0; // có dữ liệu → đúng
        cursor.close();
        db.close();
        return result;
    }
    //lay role
    public String getRole(String tk, String mk) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT quyenHan FROM NguoiDung WHERE tenDangNhap=? AND matKhau=?";
        Cursor cursor = db.rawQuery(query, new String[]{tk, mk});
        String role = "";
        if (cursor.moveToFirst()) {
            role = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return role;
    }
    //them san pham
    public void insertSanPham(String tenSanPham, double gia) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO SanPham (tenSP, gia) VALUES (?, ?)";
        db.execSQL(query, new Object[]{tenSanPham, gia});
        db.close();
    }
    //lay san pham
    public ArrayList<SanPham> getSanPham() {
        ArrayList<SanPham> sanPhamList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM SanPham";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("maSP"));
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                double gia = cursor.getDouble(cursor.getColumnIndexOrThrow("gia"));
                boolean trangThai = cursor.getInt(cursor.getColumnIndexOrThrow("trangThai")) == 1;
                SanPham sanPham = new SanPham(id, tenSP, gia, trangThai);
                sanPhamList.add(sanPham);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sanPhamList;
    }
    //lay ten san pham
    public List<String> getTenSanPham() {
        List<String> tenSanPhamList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tenSP FROM SanPham WHERE trangThai = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                tenSanPhamList.add(tenSP);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tenSanPhamList;
    }
    //lay id san pham
    public int getIdSanPham(String tenSanPham) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT maSP FROM SanPham WHERE tenSP=? AND trangThai = 1";
        Cursor cursor = db.rawQuery(query, new String[]{tenSanPham});
        int id = 0;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("maSP"));
        }
        cursor.close();
        db.close();
        return id;
    }
    //lay tien cua san pham
    public double getGiaSanPham(String tenSanPham) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT gia FROM SanPham WHERE tenSP=? AND trangThai = 1";
        Cursor cursor = db.rawQuery(query, new String[]{tenSanPham});
        double gia = 0;
        if(cursor.moveToFirst()) {
            gia = cursor.getDouble(cursor.getColumnIndexOrThrow("gia"));
        }
        cursor.close();
        db.close();
        return gia;
    }
    //cap nhat san pham
    public void updateSanPham(int maSP, String tenSP, double gia) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE SanPham SET tenSP=?, gia=? WHERE maSP=?";
        db.execSQL(query, new Object[]{tenSP, gia, maSP});
        db.close();
    }
    //xoa san pham
    public void deleteSanPham(int maSP) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE SanPham SET trangThai=0 WHERE maSP=?";
        db.execSQL(query, new Object[]{maSP});
        db.close();
    }
    //them hoa don
    public long insertHoaDonAndGetId(String ngayLap, double tongTien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ngayLap", ngayLap);
        values.put("tongTien", tongTien);

        long id = db.insert("HoaDon", null, values);
        db.close();
        return id;
    }
    //them chi tiet hoa don
    public void insertChiTietHoaDon(long maHD, int maSP, int soLuong, double donGiaLucBan) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO ChiTietHoaDon (maHD, maSP, soLuong, donGiaLucBan) VALUES (?, ?, ?,?)";
        db.execSQL(query, new Object[]{maHD, maSP, soLuong, donGiaLucBan});
        db.close();
    }
    //lay hoa don
    public ArrayList<HoaDon> getHoaDon() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM HoaDon";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("maHD"));
                String ngayLap = cursor.getString(cursor.getColumnIndexOrThrow("ngayLap"));
                double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));

                HoaDon hoaDon = new HoaDon(id, ngayLap, tongTien);
                hoaDonList.add(hoaDon);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return hoaDonList;
    }
    //lay chi tiet hoa don
    public ArrayList<ChiTietHoaDon> getChiTietHoaDon(long maHD) {
        ArrayList<ChiTietHoaDon> listChiTiet = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT sp.tenSP, cthd.soLuong, cthd.donGiaLucBan " +
                "FROM ChiTietHoaDon cthd " +
                "JOIN SanPham sp ON cthd.maSP = sp.maSP " +
                "WHERE cthd.maHD = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maHD)});

        if (cursor.moveToFirst()) {
            do {
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
                double gia = cursor.getDouble(cursor.getColumnIndexOrThrow("donGiaLucBan"));

                ChiTietHoaDon item = new ChiTietHoaDon(tenSP, soLuong, gia);
                listChiTiet.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listChiTiet;
    }




}


