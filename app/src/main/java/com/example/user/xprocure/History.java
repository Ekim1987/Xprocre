package com.example.user.xprocure;

/**
 * Created by user on 25/01/2017.
 */

public class History {
    private String date,place,title;


    public History(){
    }
    public  History(String date,String place,String title){
        this.date=date;
        this.place=place;
        this.title=title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
