package com.example.oop_ui_test.Classes;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Rental {
    private String id;
    private Item item;
    private int amount;
    private String status;


    public Rental(Item item, int amount){
        this.id = item.getId();
        this.item = item;
        this.amount = amount;
        this.status = "On-going";
    }
    public Rental(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void returnItem(){
        if(this.status != "Returned"){
            this.item.setStock(item.getStock()+this.amount);
            this.status = "Returned";
        }else {
            System.out.println("Error! This have been returned.");
        }

    }

    public void writeRentalfile(){
        try {
            Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
            FileWriter myWriter = new FileWriter(path.toString()+"\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\Rental.txt",true);
            myWriter.write(this.id+"@"+this.amount+"@"+this.status+"@"+this.item.getId()+"\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printRentalInfo(){

        System.out.println( "Item: "+ this.item.getTitle() +"\n"+
                            "Amount: " + this.amount + "\n"+
                            "Status: " + this.status+"\n");
    }

}
