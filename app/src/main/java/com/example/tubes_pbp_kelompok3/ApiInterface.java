package com.example.tubes_pbp_kelompok3;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    ////// API PELAMAR //////
    @GET("pelamar/tampil")
    Call<List<PelamarDAO>> getPelamar();

    @GET("pelamar/cari/{email}")
    Call<PelamarDAO> getPelamar(@Path("email")String email);

    @PUT("pelamar/ubah/{email}")
    Call<String> ubahPelamar(@Field("nama") String nama,
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


    ///// API PERUSAHAAN ////
    @GET("perusahaan/tampil")
    Call<List<PerusahaanDAO>> getPerusahaan();

    @GET("perusahaan/cari/{id}")
    Call<PerusahaanDAO> getPerusahaan(@Path("email")String email);

    @PUT("perusahaan/ubah/{email}")
    Call<String> ubahPerusahaan(@Field("nama") String nama,
                             @Field("email") String email,
                             @Field("password") String password,
                             @Field("pekerjaan") String pekerjaan,
                             @Field("usiaMin") String usiaMin,
                             @Field("usiaMax") String usiaMax,
                             @Field("pendidikan") String pendidikan,
                             @Field("penempatan") String penempatan,
                             @Field("gajiBulanan") String gajiBulanan);

    @POST("perusahaan/tambah")
    @FormUrlEncoded
    Call<String> addPerusahaan(@Field("nama") String nama,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("pekerjaan") String pekerjaan,
                               @Field("usiaMin") String usiaMin,
                               @Field("usiaMax") String usiaMax,
                               @Field("pendidikan") String pendidikan,
                               @Field("penempatan") String penempatan,
                               @Field("gajiBulanan") String gajiBulanan);

    /////////////////////////////////////////////////////////////////////////

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
