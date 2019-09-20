package com.example.tubes_pbp_kelompok3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity {
    /* private List<StudentDAO> mListStudent = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mListStudent= new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recycleAdapter = new RecycleAdapter(this,mListStudent);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapter);
        setRecycleView();
    }

    private void setRecycleView() {
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<List<StudentDAO>> studentDAOCall=apiService.getStudents();

        studentDAOCall.enqueue(new Callback<List<StudentDAO>>() {
            @Override
            public void onResponse(Call<List<StudentDAO>> call, Response<List<StudentDAO>> response) {
                mListStudent.addAll(response.body());
                recycleAdapter.notifyDataSetChanged();
                Toast.makeText(ShowActivity.this,"welcome",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<StudentDAO>> call, Throwable t) {
                Toast.makeText(ShowActivity.this,"Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
            }
        });
    } */
}
