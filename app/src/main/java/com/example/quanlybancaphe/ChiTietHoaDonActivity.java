package com.example.quanlybancaphe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import com.example.quanlybancaphe.database.DatabaseHelper;
import com.example.quanlybancaphe.entities.ChiTietHoaDon;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    Button btnBack;
    TextView txtNgayThang, txtMaHoaDon, txtTongCong;
    ListView listviewChiTiet;
    DatabaseHelper dbHelper;
    ArrayList<ChiTietHoaDon> itemList;
    ArrayAdapter<ChiTietHoaDon> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        dbHelper = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBack);

        txtNgayThang = findViewById(R.id.txtNgayGio);
        txtMaHoaDon = findViewById(R.id.txtMaHD);
        txtTongCong = findViewById(R.id.txtTongCong);

        listviewChiTiet = findViewById(R.id.listviewChiTiet);

        Intent intent = getIntent();
        int maHD = intent.getIntExtra("maHD", -1);

        txtMaHoaDon.setText("Chi Tiết Hóa Đơn #" + maHD);
        txtNgayThang.setText(intent.getStringExtra("ngayLap"));
        txtTongCong.setText(intent.getStringExtra("tongTien"));

        itemList = dbHelper.getChiTietHoaDon(maHD);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listviewChiTiet.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}