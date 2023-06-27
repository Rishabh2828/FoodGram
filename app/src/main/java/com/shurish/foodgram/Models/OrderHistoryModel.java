package com.shurish.foodgram.Models;

import com.google.firebase.Timestamp;

public class OrderHistoryModel {

    String text1, text2, text3, img;
    Timestamp timestamp;

    public OrderHistoryModel() {
    }

    public OrderHistoryModel(String text1, String text2, String text3, String img, Timestamp timestamp) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.img = img;
        this.timestamp = timestamp;
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

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
