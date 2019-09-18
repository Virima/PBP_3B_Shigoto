package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePageActivity extends AppCompatActivity {
    private Button mDaftarBtn;
    private Button mMasukBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        setAtribut();

        mDaftarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startDaftar();
            }
        });

        mMasukBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startMasuk();
            }
        });
    }

    private void startDaftar(){
        Intent intent = new Intent(getApplicationContext(), DaftarSbgActivity.class);
        startActivity(intent);
    }

    private void startMasuk(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void setAtribut(){
        mDaftarBtn = findViewById(R.id.btnDaftarWelcome);
        mMasukBtn = findViewById(R.id.btnMasukWelcome);
    }
}
