package com.example.oop_ui_test.Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ManageItem {

    public static ArrayList<Item> items = new ArrayList<Item>();

    public static void readFile(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(path.toString()+"\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int cnt = 1;
        while(fileScanner.hasNextLine()){
            Item item = new Item();

            String line = fileScanner.nextLine();
            StringTokenizer inReader = new StringTokenizer(line,",");

            item.setId(inReader.nextToken());
            item.setTitle(inReader.nextToken());
            item.setRentalType(inReader.nextToken());
            item.setLoanType(inReader.nextToken());
            item.setStock(Integer.parseInt(inReader.nextToken()));
            item.setRentalFee(Double.parseDouble(inReader.nextToken()));
            item.setImgSrc(path.toString()+"\\src\\main\\resources\\com\\example\\oop_ui_test\\img\\"+cnt+".png");
            cnt++;

            try{String tmp = inReader.nextToken();
                item.setGenre(tmp);
            }catch (NoSuchElementException e){

            }

            items.add(item);
        }
    }

    public static void saveFile(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();

        try {
            FileWriter file = new FileWriter(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt");

            for(Item item: items) {
                file.write(item.getId()+","+item.getTitle()+","+item.getRentalType()+","+item.getLoanType()+","+item.getStock()+","+item.getRentalFee());
                if(item.getGenre() != null){
                    file.write(","+item.getGenre()+"\n");
                }else{
                    file.write("\n");
                }

            }

            file.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int find(String id){
        for(Item item: items){
            if(item.getId().matches(id)){
                return items.indexOf(item);
            }
        }
        return -1;
    }
}
