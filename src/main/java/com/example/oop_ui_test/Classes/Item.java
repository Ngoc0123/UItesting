package com.example.oop_ui_test.Classes;

public class Item {
    private String id;
    private String title;
    private String rentalType;
    private String loanType;
    private int stock;
    private double rentalFee;
    private String genre;
    private boolean availability;

    public Item(){}

    public Item(String id, String title, String rentalType, String loanType, int stock, double rentalFee, String genre, boolean availability){
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.stock = stock;
        this.rentalFee = rentalFee;
        this.genre = genre;
        this.availability = true;
    }
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
//
    public void printItemInfo(){
        System.out.println( "Title: "+this.title+"\n"+
                            "ID: "+this.id+"\n"+
                            "Stock: "+this.stock+"\n");
    }



}
