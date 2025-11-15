package com.example.quanlybancaphe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlybancaphe.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.quanlybancaphe.entities.SanPham;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    public SanPhamAdapter(Context context, ArrayList<SanPham> sanPhamList) {
        super(context, 0, sanPhamList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SanPham sanPham = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sanpham, parent, false);
        }

        TextView txtTenSP = convertView.findViewById(R.id.txtTenHD);
        TextView txtGiaSP = convertView.findViewById(R.id.txtGiaHD);
        TextView txtHuy = convertView.findViewById(R.id.txtHuy);


        if (sanPham != null) {
            txtTenSP.setText(sanPham.getTenSP());
            DecimalFormat dcf = new DecimalFormat("#,###");
            String giaDinhDang = dcf.format(sanPham.getGia());
            txtGiaSP.setText(giaDinhDang+" VND");
            if (sanPham.isTrangThai()) {
                txtHuy.setText("Đang bán");
            } else {
                txtHuy.setText("Dừng Bán");
            }
        }

        return convertView;
    }
}
