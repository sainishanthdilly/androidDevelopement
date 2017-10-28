package com.example.nishanth.midtermexam;

/**
 * Created by nishanth on 3/19/2017.
 */

public class Movie {

    String imgUrl;
    String name;
    String price;
    String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.name == ((Movie)obj).name)
            return true;
        return false;


    }
}
