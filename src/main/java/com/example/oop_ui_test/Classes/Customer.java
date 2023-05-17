package com.example.oop_ui_test.Classes;

import java.util.ArrayList;

public class Customer extends Account{
  private String id;
  private String name;
  private String address;
  private String phone;
  private int rentalNumber;
  private String level;





  private ArrayList<Rental> rentals;

  public String getLevel() {
    return level;
  }
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public int getRentalNumber() {
    return rentalNumber;
  }

  public ArrayList<Rental> getRentals() {
    return rentals;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setRentalNumber(int rentalNumber) {
    this.rentalNumber = rentalNumber;
  }
}
