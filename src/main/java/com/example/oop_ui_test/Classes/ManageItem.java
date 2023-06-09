package com.example.oop_ui_test.Classes;

import javafx.scene.image.Image;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ManageItem {
    private static ManageItem manageItemObj;
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static ManageItem getInstance(){
        if(manageItemObj == null){
            manageItemObj = new ManageItem();
        }
        return manageItemObj;
    }


    public static void readFile(){
        ManageItem.items = new ArrayList<Item>();
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(path.toString()+"\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
            item.setImgSrc("file:///"+path+"\\src\\main\\resources\\com\\example\\oop_ui_test\\img\\"+item.getRentalType()+".png");

            try{String tmp = inReader.nextToken();
                item.setGenre(tmp);
            }catch (NoSuchElementException e){

            }

            items.add(item);
        }
    }

    public static void saveFile(){
        sort(SORT_BY_ID);
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

    public static boolean isExist(String id){

        for (Item item : items) {
            if (item.getId().substring(0,4).matches(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean SORT_BY_NAME = true;
    public static boolean SORT_BY_ID = false;
    public static void sort(boolean type){
        if(type)
            items.sort(new Comparator<Item>() {
                @Override
                public int compare(Item a, Item b) {
                    return a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase());
                }
            });
        else
            items.sort(new Comparator<Item>() {
                @Override
                public int compare(Item a, Item b) {
                    return a.getId().compareTo(b.getId());
                }
            });
    }
}
