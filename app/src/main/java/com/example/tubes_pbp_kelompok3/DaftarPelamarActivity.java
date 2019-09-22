package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarPelamarActivity extends AppCompatActivity {

    public EditText mNama, mEmail, mPassword;
    private EditText mAlamat, mUsia, mPekerjaanTerakhir, mGaji;
    private RadioGroup mRadioGroup;
    private RadioButton mJenisKelamin;
    private Spinner mPendidikanTerakhir, mTahunWisuda;
    private Spinner mPekerjaanDiinginkan, mLokasi;
    private Button mRegisterBtn;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_sebagai_pelamar);
        setAtribut();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickRegister();
            }
        });
    }

    private void setAtribut() {
        mNama = findViewById(R.id.namaPlmr);
        mEmail = findViewById(R.id.emailPlmr);
        mPassword = findViewById(R.id.passwordPlmr);
        mAlamat = findViewById(R.id.alamatPlmr);
        mUsia = findViewById(R.id.usiaPlmr);
        mPekerjaanTerakhir = findViewById(R.id.pekerjaanTerakhirPlmr);
        mGaji = findViewById(R.id.gajiBulananPlmr);
        mRadioGroup = findViewById(R.id.RadioGroupJKPlmr);
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        mJenisKelamin = findViewById(selectedId);
        mPendidikanTerakhir = findViewById(R.id.pendidikanPlmr);
        mTahunWisuda = findViewById(R.id.tahunWisudaPlmr);
        mPekerjaanDiinginkan = findViewById(R.id.pekerjaanImpianPlmr);
        mLokasi = findViewById(R.id.penempatanPlmr);

        mRegisterBtn = findViewById(R.id.btnDaftarPlmr);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void OnClickRegister(){
        if(mNama.getText().toString().isEmpty() ||
                mEmail.getText().toString().isEmpty() ||
                mPassword.getText().toString().isEmpty() ||
                mAlamat.getText().toString().isEmpty() ||
                mUsia.getText().toString().isEmpty() ||
                mPekerjaanTerakhir.getText().toString().isEmpty() ||
                mGaji.getText().toString().isEmpty() ||
                mPendidikanTerakhir.getSelectedItem().toString().isEmpty() ||
                mTahunWisuda.getSelectedItem().toString().isEmpty() ||
                mPekerjaanDiinginkan.getSelectedItem().toString().isEmpty() ||
                mLokasi.getSelectedItem().toString().isEmpty())
        {
            Toast.makeText(this, "Isilah semua field yang disediakan!",Toast.LENGTH_SHORT).show();
        }else{
            /*
            int selectedId=mRadioGroup.getCheckedRadioButtonId();
            mJenisKelamin=findViewById(selectedId);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<String> PelamarDAOCALL=apiService.addPelamar
                    (mNama.getText().toString(),
                        mEmail.getText().toString(),
                        mPassword.getText().toString(),
                        mAlamat.getText().toString(),
                        mUsia.getText().toString(),
                        mJenisKelamin.getText().toString(),
                        mPekerjaanTerakhir.getText().toString(),
                        mPendidikanTerakhir.getSelectedItem().toString(),
                        mTahunWisuda.getSelectedItem().toString(),
                        mPekerjaanDiinginkan.getSelectedItem().toString(),
                        mLokasi.getSelectedItem().toString(),
                        mGaji.getText().toString());

            PelamarDAOCALL.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(DaftarPelamarActivity.this, "Success",Toast.LENGTH_SHORT).show();
                    startIntent();
                }

                public  void onFailure(Call<String> call, Throwable t){
                    Toast.makeText(DaftarPelamarActivity.this,"Permasalahan Koneksi",Toast.LENGTH_SHORT).show();
                }

            }); */
            String nama= mNama.getText().toString();
            String email=mEmail.getText().toString();
            String password=mPassword.getText().toString();
            String alamat=mAlamat.getText().toString();
            String usia=mUsia.getText().toString();
            String jenisKelamin=mJenisKelamin.getText().toString();
            String pekerjaanTerakhir=mPekerjaanTerakhir.getText().toString();
            String pendidikanTerakhir=mPendidikanTerakhir.getSelectedItem().toString();
            String tahunWisuda=mTahunWisuda.getSelectedItem().toString();
            String pekerjaanImpian=mPekerjaanDiinginkan.getSelectedItem().toString();
            String lokasi=mLokasi.getSelectedItem().toString();
            String ekspektasiGaji=mGaji.getText().toString();
            addPelamar(nama, email, password, alamat, usia, jenisKelamin, pekerjaanTerakhir, pendidikanTerakhir,
                    tahunWisuda, pekerjaanImpian, lokasi, ekspektasiGaji);

            Toast.makeText(DaftarPelamarActivity.this, "Success",Toast.LENGTH_SHORT).show();
            startIntent();
        }
    }

    private void startIntent(){
        Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void addPelamar(String nama, String email, String password, String alamat, String usia,
                               String jenis_kelamin, String pekerjaanTerakhir, String pendidikanTerakhir,
                                String tahunWisuda, String pekerjaanImpian, String lokasi, String gaji)
    {
        PelamarDAO pelamarDAO = new PelamarDAO(nama, email, password, alamat, usia, jenis_kelamin,
                pekerjaanTerakhir, pendidikanTerakhir, tahunWisuda, pekerjaanImpian, lokasi, gaji);
        mDatabase.child("Pelamar").child(email).setValue(pelamarDAO);
    }


}
