package com.example.ergroproxy.harakarailway;

public class Data01 {

    String firstname,lastname,passid,radiobuttton,dateselec,sp;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassid() {
        return passid;
    }

    public void setPassid(String passid) {
        this.passid = passid;
    }

    public String getRadiobuttton() {
        return radiobuttton;
    }

    public void setRadiobuttton(String radiobuttton) {
        this.radiobuttton = radiobuttton;
    }

    public String getDateselec() {
        return dateselec;
    }

    public void setDateselec(String dateselec) {
        this.dateselec = dateselec;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public Data01(String firstname, String lastname, String passid, String radiobuttton, String dateselec, String sp) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.passid = passid;
        this.radiobuttton = radiobuttton;
        this.dateselec = dateselec;
        this.sp = sp;

    }
}
