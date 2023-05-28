package com.example.oop_ui_test.Classes;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Rental {
    private String id;
    private String status;

    public Rental(String id, String Status){
        this.id = id;
        this.status = "On-going";
    }
    public Rental(){}


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }






}
