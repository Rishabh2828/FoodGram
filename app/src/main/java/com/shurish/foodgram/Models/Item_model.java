package com.shurish.foodgram.Models;

public class Item_model {
    String img;

    String text;


    public Item_model() {
    }

    public Item_model(String img, String text) {
        this.img = img;
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
