package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarSbgActivity extends AppCompatActivity {

    private Button mPelamarBtn;
    private Button mPerusahaanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_daftar);
        setAtribut();
        mPelamarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDaftarPelamar();
            }
        });

        mPerusahaanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDaftarPerusahaan();
            }
        });
    }

    private void startDaftarPelamar() {
        Intent intent = new Intent(getApplicationContext(), DaftarPelamarActivity.class);
        startActivity(intent);
    }

    private void startDaftarPerusahaan() {
        Intent intent = new Intent(getApplicationContext(), DaftarPerusahaanActivity.class);
        startActivity(intent);
    }

    private void setAtribut() {
        mPelamarBtn = findViewById(R.id.btnSbgPelamar);
        mPerusahaanBtn = findViewById(R.id.btnSbgPerusahaan);
    }


}
