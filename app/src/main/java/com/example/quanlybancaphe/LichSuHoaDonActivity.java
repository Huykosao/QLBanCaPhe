package com.example.quanlybancaphe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.quanlybancaphe.adapters.HoaDonAdapter;
import com.example.quanlybancaphe.database.DatabaseHelper;
import com.example.quanlybancaphe.entities.HoaDon;

public class LichSuHoaDonActivity extends AppCompatActivity {
    Button btnBack;
    ListView listLichSuHoaDon;
    ArrayList<HoaDon> hoaDonList;
    HoaDonAdapter adapter;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_su_hoa_don);

        dbHelper = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBack);

        listLichSuHoaDon = findViewById(R.id.listLichSuHoaDon);

        hoaDonList = dbHelper.getHoaDon();
        adapter = new HoaDonAdapter(this, hoaDonList);
        listLichSuHoaDon.setAdapter(adapter);

        listLichSuHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon selectedHoaDon = hoaDonList.get(position);

                Intent intent = new Intent(LichSuHoaDonActivity.this, ChiTietHoaDonActivity.class);
                intent.putExtra("maHD", selectedHoaDon.getMaHD());
                intent.putExtra("ngayLap", selectedHoaDon.getNgayLap());
                DecimalFormat dcf = new DecimalFormat("#,###");
                intent.putExtra("tongTien", dcf.format(selectedHoaDon.getTongTien()) + " VND");

                startActivity(intent);

            }
        });
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