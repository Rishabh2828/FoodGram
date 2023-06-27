package com.shurish.foodgram.Models;

public class meal_model {
    String img;
    String text1, text2, text3;

    String quantity;

    public meal_model(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public meal_model() {
    }

    public meal_model(String img, String text1, String text2, String text3) {
        this.img = img;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
