package com.efpro.iweight;

/**
 * Created by rzapalupi on 5/25/2017.
 */

public class User {

    //private  int idu;
    private String username;
    private String password;
    private String namalengkap;
    private int umur;
    private String gender;
    private double beratbadan;
    private double tinggibadan;

    public User(){

    }

    public User(String username, String namalengkap,
                int umur, String gender, double beratbadan, double tinggibadan){
        this.username=username;
        this.namalengkap=namalengkap;
        this.umur=umur;
        this.gender=gender;
        this.beratbadan=beratbadan;
        this.tinggibadan=tinggibadan;
    }

    public User(String username, String password, String namalengkap,
                int umur, String gender, double beratbadan, double tinggibadan){
        //this.idu=idu;
        this.username=username;
        this.password=password;
        this.namalengkap=namalengkap;
        this.umur=umur;
        this.gender=gender;
        this.beratbadan=beratbadan;
        this.tinggibadan=tinggibadan;
    }

//    public void setIdu(int idu) {
//        this.idu = idu;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBeratbadan(double beratbadan) {
        this.beratbadan = beratbadan;
    }

    public void setTinggibadan(double tinggibadan) {
        this.tinggibadan = tinggibadan;
    }


//    public int getIdu() {
//        return idu;
//    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public int getUmur() {
        return umur;
    }

    public String getGender() {
        return gender;
    }

    public double getBeratbadan() {
        return beratbadan;
    }

    public double getTinggibadan() {
        return tinggibadan;
    }





}
