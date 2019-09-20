package com.example.tubes_pbp_kelompok3;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("pelamar/tampil")
    Call<List<PelamarDAO>> getPelamar();

    @GET("pelamar/cari/{id}")
    Call<PelamarDAO> getPelamar(@Path("id")String id);

    @POST("pelamar/tambah")
    @FormUrlEncoded
    Call<String> addPelamar(@Field("nama") String nama,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("alamat") String alamat,
                            @Field("usia") String usia,
                            @Field("jenis_kelamin") String jenis_kelamin,
                            @Field("pekerjaan_terakhir") String pekerjaan_terakhir,
                            @Field("pendidikan_terakhir") String pendidikan_terakhir,
                            @Field("tahun_wisuda") String tahun_wisuda,
                            @Field("pekerjaan_impian") String pekerjaan_impian,
                            @Field("lokasi") String lokasi,
                            @Field("ekspektasi_gaji") String ekspektasi_gaji);


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password);
}
