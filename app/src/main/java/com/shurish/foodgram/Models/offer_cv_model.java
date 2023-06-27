package com.shurish.foodgram.Models;

public class offer_cv_model {
    String text1, text2, textbtn;

    String img;

    String price, productname, couponapplied;

    public String getCouponapplied() {
        return couponapplied;
    }

    public void setCouponapplied(String couponapplied) {
        this.couponapplied = couponapplied;
    }

    public offer_cv_model(String couponapplied) {
        this.couponapplied = couponapplied;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public offer_cv_model(String price, String productname) {
        this.price = price;
        this.productname = productname;
    }

    public offer_cv_model() {
    }

    public offer_cv_model(String text1, String text2, String textbtn, String img) {
        this.text1 = text1;
        this.text2 = text2;
        this.textbtn = textbtn;
        this.img = img;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getTextbtn() {
        return textbtn;
    }

    public void setTextbtn(String textbtn) {
        this.textbtn = textbtn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
