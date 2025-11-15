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

import com.example.quanlybancaphe.entities.HoaDon;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    public HoaDonAdapter(Context context, ArrayList<HoaDon> hoaDonList) {
        super(context, 0, hoaDonList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HoaDon hoaDon = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hoadon, parent, false);
        }

        TextView txtTenHD = convertView.findViewById(R.id.txtTenHD);
        TextView txtGiaHD = convertView.findViewById(R.id.txtGiaHD);

        if (hoaDon != null) {
            txtTenHD.setText(hoaDon.getNgayLap());
            DecimalFormat dcf = new DecimalFormat("#,###");
            String giaDinhDang = dcf.format(hoaDon.getTongTien());
            txtGiaHD.setText(giaDinhDang+" VND");
        }

        return convertView;
    }



}
