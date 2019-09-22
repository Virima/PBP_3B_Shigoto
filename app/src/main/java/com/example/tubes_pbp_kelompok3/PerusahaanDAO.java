package com.example.tubes_pbp_kelompok3;

public class PerusahaanDAO {
    String namaP, emailP, passwordP, jenis_pekerjaan, pendidikan_minimum;
    String lokasiP, gajiP;
    String usiaMin, usiaMax;

    public String getNamaP() {return namaP;}
    public void setNamaP(String namaP) {this.namaP=namaP;}

    public String getEmailP() {return emailP;}
    public void setEmailP(String emailP) {this.emailP=emailP;}

    public String getPasswordP() {return passwordP;}
    public void setPasswordP(String passwordP) {this.passwordP=passwordP;}

    public String getJenis_pekerjaan() {return jenis_pekerjaan;}
    public void setJenis_pekerjaan(String jenis_pekerjaan) {this.jenis_pekerjaan=jenis_pekerjaan;}

    public String getPendidikan_minimum() {return pendidikan_minimum;}
    public void setPendidikan_minimum(String pendidikan_minimum) {this.pendidikan_minimum=pendidikan_minimum;}

    public String getLokasiP() {return lokasiP;}
    public void setLokasiP(String lokasiP) {this.lokasiP=lokasiP;}

    public String getGajiP() {return gajiP;}
    public void setGajiP(String gajiP) {this.gajiP=gajiP;}

    public String getUsiaMin() {return usiaMin;}
    public void setUsiaMin(String usiaMin) {this.usiaMin=usiaMin;}

    public String getUsiaMax() {return usiaMax;}
    public void setUsiaMax(String usiaMax) {this.usiaMax=usiaMax;}

    public PerusahaanDAO(String namaP, String emailP, String passwordP, String jenis_pekerjaan, String pendidikan_minimum,
                         String lokasiP, String gajiP, String usiaMin, String usiaMax)
    {
        this.namaP=namaP;
        this.emailP=emailP;
        this.passwordP=passwordP;
        this.jenis_pekerjaan=jenis_pekerjaan;
        this.pendidikan_minimum=pendidikan_minimum;
        this.lokasiP=lokasiP;
        this.gajiP=gajiP;
        this.usiaMin=usiaMin;
        this.usiaMax=usiaMax;
    }
}
