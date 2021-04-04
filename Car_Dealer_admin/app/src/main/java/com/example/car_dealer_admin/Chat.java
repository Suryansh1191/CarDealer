package com.example.car_dealer_admin;

public class Chat {

    String Phone,Transmission,brand,describtion,fuel,img_url,km,name,year,finalIMG ;

    public Chat() {
    }

    public Chat(String phone, String transmission, String brand, String describtion, String fuel, String img_url, String km, String name, String year) {
        Phone = phone;
        Transmission = transmission;
        this.brand = brand;
        this.describtion = describtion;
        this.fuel = fuel;
        this.img_url = img_url;
        this.km = km;
        this.name = name;
        this.year = year;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //    private String mName;
//    private String mImageUrl;
//
//    public upload(String name, String imageUrl) {
//        if (name.trim().equals("")) {
//            name = "No Name";
//        }
//        mName = name;
//        mImageUrl = imageUrl;
//    }
//
//
//    public void Upload() {
//        //empty constructor needed
//    }
////    public void Upload(String name, String imageUrl) {
////        if (name.trim().equals("")) {
////            name = "No Name";
////        }
////        mName = name;
////        mImageUrl = imageUrl;
////    }
//    public String getName() {
//        return mName;
//    }
//    public void setName(String name) {
//        mName = name;
//    }
//    public String getImageUrl() {
//        return mImageUrl;
//    }
//    public void setImageUrl(String imageUrl) {
//        mImageUrl = imageUrl;
//    }
}
