package com.example.demo2.entity;

public class Board {


    private String bmemo;
    private String bcontent;
    private String btitle;
    private String uid;
    private String no; //글번호 증가


    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
