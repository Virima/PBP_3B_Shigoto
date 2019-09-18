package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button mDaftarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);
        setAtribut();
        mDaftarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startDaftar();
            }
        });
    }

    private void startDaftar(){
        Intent intent = new Intent(getApplicationContext(), DaftarSbgActivity.class);
        startActivity(intent);
    }

    private void setAtribut(){
        mDaftarBtn = findViewById(R.id.btnDaftarSkrng);
    }
}
