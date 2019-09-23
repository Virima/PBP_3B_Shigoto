package com.example.tubes_pbp_kelompok3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DaftarPerusahaanActivity extends AppCompatActivity {

    public EditText mNama, mEmail, mPassword;
    private Spinner mPekerjaan, mPendidikan, mPenempatan;
    private EditText mUsiaMin, mUsiaMax, mGajiBulanan;
    private Button mRegisterBtn;
    private static final String TAG = "LoginActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_sebagai_perusahaan);
        setAtribut();

        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickRegister();
                signUp();
            }
        });
    }

    private void setAtribut(){
        mNama = findViewById(R.id.namaLengkap);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPekerjaan = findViewById(R.id.pekerjaan);
        mUsiaMin = findViewById(R.id.usiaMin);
        mUsiaMax = findViewById(R.id.usiaMax);
        mPendidikan = findViewById(R.id.pendidikan);
        mPenempatan = findViewById(R.id.penempatan);
        mGajiBulanan = findViewById(R.id.gajiBulanan);
        mRegisterBtn = findViewById(R.id.btnDaftar);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void onClickRegister(){
        if(mNama.getText().toString().isEmpty() ||
                mEmail.getText().toString().isEmpty() ||
                mPassword.getText().toString().isEmpty() ||
                mPekerjaan.getSelectedItem().toString().isEmpty() ||
                mUsiaMin.getText().toString().isEmpty() ||
                mUsiaMax.getText().toString().isEmpty() ||
                mPendidikan.getSelectedItem().toString().isEmpty() ||
                mPenempatan.getSelectedItem().toString().isEmpty() ||
                mGajiBulanan.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Isilah semua field yang disediakan!",Toast.LENGTH_SHORT).show();
        }else{
            /*
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<String> PerusahaanDAOCALL = apiService.addPerusahaan(
                    mNama.getText().toString(),
                    mEmail.getText().toString(),
                    mPassword.getText().toString(),
                    mPekerjaan.getSelectedItem().toString(),
                    mUsiaMin.getText().toString(),
                    mUsiaMax.getText().toString(),
                    mPendidikan.getSelectedItem().toString(),
                    mPenempatan.getSelectedItem().toString(),
                    mGajiBulanan.getText().toString());

            PerusahaanDAOCALL.enqueue(new Callback<String>(){
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(DaftarPerusahaanActivity.this, "Success",Toast.LENGTH_SHORT).show();
                    startIntent();

                }

                public  void onFailure(Call<String> call, Throwable t){
                    Toast.makeText(DaftarPerusahaanActivity.this,"Permasalahan Koneksi",Toast.LENGTH_SHORT).show();
                }
            }); */
            String nama= mNama.getText().toString();
            String email=mEmail.getText().toString();
            String password=mPassword.getText().toString();
            String pekerjaan=mPekerjaan.getSelectedItem().toString();
            String usiaMin=mUsiaMin.getText().toString();
            String usiaMax=mUsiaMax.getText().toString();
            String pendidikan=mPendidikan.getSelectedItem().toString();
            String penempatan=mPenempatan.getSelectedItem().toString();
            String gaji=mGajiBulanan.getText().toString();

            addPerusahaan(nama, email, password, pekerjaan, pendidikan, penempatan,gaji, usiaMin, usiaMax);

            Toast.makeText(DaftarPerusahaanActivity.this, "Success",Toast.LENGTH_SHORT).show();
            startIntent();
        }
    }

    private void startIntent(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void addPerusahaan(String nama, String email, String password, String pekerjaan, String pendidikan,
                               String penempatan, String gajiBulanan, String usiaMin, String usiaMax)
    {
        PerusahaanDAO perusahaanDAO = new PerusahaanDAO(nama, email, password, pekerjaan, pendidikan, penempatan,
                                    gajiBulanan, usiaMin, usiaMax);
        mDatabase.child("Perusahaan").child(nama).setValue(perusahaanDAO);
    }

    //fungsi ini untuk mendaftarkan data pengguna ke Firebase
    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(DaftarPerusahaanActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // membuat User admin baru
        writeNewAdmin(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(DaftarPerusahaanActivity.this, LoginActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewAdmin(String userId, String name, String email) {
        AdminDAO admin = new AdminDAO(name, email);

        mDatabase.child("admins").child(userId).setValue(admin);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError("Required");
            result = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            mPassword.setError("Required");
            result = false;
        } else {
            mPassword.setError(null);
        }

        return result;
    }

}
