package com.example.tubes_pbp_kelompok3;

public class PelamarDAO {
    String nama, email, password, alamat;
    String jenis_kelamin, pekerjaan_terakhir, pendidikan_terakhir;
    String pekerjaan_diinginkan, lokasi_kerja;
    int usia, tahun_wisuda;
    double ekspektasi_gaji;

    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama=nama;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email=email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password=password;}

    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat=alamat;}

    public String getJenis_Kelamin() {return jenis_kelamin;}
    public void setJenis_kelamin(String jenis_kelamin) {this.jenis_kelamin=jenis_kelamin;}

    public String getPekerjaan_terakhir() {return pekerjaan_terakhir;}
    public void setPekerjaan_terakhir(String pekerjaan_terakhir) {this.pekerjaan_terakhir=pekerjaan_terakhir;}

    public String getPendidikan_terakhir() {return pendidikan_terakhir;}
    public void setPendidikan_terakhir(String pendidikan_terakhir) {this.pendidikan_terakhir=pendidikan_terakhir;}

    public String getPekerjaan_diinginkan() {return pekerjaan_diinginkan;}
    public void setPekerjaan_diinginkan(String pekerjaan_diinginkan) {this.pekerjaan_diinginkan=pekerjaan_diinginkan;}

    public String getLokasi_kerja() {return lokasi_kerja;}
    public void setLokasi_kerja(String lokasi_kerja) {this.lokasi_kerja=lokasi_kerja;}

    public int getUsia() {return usia;}
    public void setUsia(int usia) {this.usia=usia;}

    public int getTahun_wisuda() {return tahun_wisuda;}
    public void setTahun_wisuda(int tahun_wisuda) {this.tahun_wisuda=tahun_wisuda;}

    public double getEkspektasi_gaji() {return ekspektasi_gaji;}
    public void setEkspektasi_gaji(double ekspektasi_gaji ) {this.ekspektasi_gaji=ekspektasi_gaji;}

    public PelamarDAO(String nama, String email, String password, String alamat, int usia, String jenis_kelamin,
                        String pekerjaan_terakhir, String pendidikan_terakhir, int tahun_wisuda,
                        String pekerjaan_diinginkan, String lokasi_kerja, int ekspektasi_gaji) {
        this.nama=nama;
        this.email=email;
        this.password=password;
        this.alamat=alamat;
        this.usia=usia;
        this.jenis_kelamin=jenis_kelamin;
        this.pekerjaan_terakhir=pekerjaan_terakhir;
        this.pendidikan_terakhir=pendidikan_terakhir;
        this.tahun_wisuda=tahun_wisuda;
        this.pekerjaan_diinginkan=pekerjaan_diinginkan;
        this.lokasi_kerja=lokasi_kerja;
        this.ekspektasi_gaji=ekspektasi_gaji;
    }

}
