package com.example.oop_ui_test.Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ManageCustomer {
    public static ArrayList<Customer> customersList = new ArrayList<Customer>();


    public  static void readFile(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\customers.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (fileScanner.hasNextLine()) {
            Customer customer = new Customer();
            String line = fileScanner.nextLine();
            StringTokenizer inReader = new StringTokenizer(line, ",");
            if (inReader.countTokens() != 9) {

            } else {
                customer.setId(inReader.nextToken());
                customer.setName(inReader.nextToken());
                customer.setAddress(inReader.nextToken());
                customer.setPhone(inReader.nextToken());
                customer.setRentalNumber(Integer.parseInt(inReader.nextToken()));
                customer.setReturned(Integer.parseInt(inReader.nextToken()));
                if(customer.getReturned() > 5){
                    customer.setLevel("VIP");
                }else if (customer.getReturned() > 3){
                    customer.setLevel("Regular");
                }else {
                    customer.setLevel("Guest");
                }
                customer.setUsername(inReader.nextToken());
                customer.setPassword(inReader.nextToken());
                customer.setRewardPoint(Integer.parseInt(inReader.nextToken()));


                for(int i  = 1; i <= customer.getRentalNumber(); i++){
                    String line2 = fileScanner.nextLine();
                    StringTokenizer Reader2 = new StringTokenizer(line2,",");
                    Rental newrental = new Rental();
                    newrental.setId(Reader2.nextToken());
                    newrental.setAmount(Integer.parseInt(Reader2.nextToken()));
                    newrental.setStatus(Reader2.nextToken());

                    customer.getRentals().add(newrental);

                }

                customersList.add(customer);

            }
        }


    }

    public  static void saveFile(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();

            try {
                FileWriter file = new FileWriter(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\customers.txt");

                for(Customer customer: customersList) {
                    file.write(customer.getId() + "," + customer.getName() + "," + customer.getAddress() + "," + customer.getPhone() + "," + customer.getRentalNumber() + "," + customer.getReturned()
                        + "," + customer.getUsername() + "," + customer.getPassword()+","+customer.getRewardPoint()+"\n");
                    for(Rental rental: customer.getRentals()){
                        file.write(rental.getId()+","+rental.getAmount()+","+rental.getStatus()+"\n");
                    }
                }

                file.close();


            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

    }

    public static void updateLevel(int i){
        int tmp = customersList.get(i).getReturned();
        if(tmp > 5){
            customersList.get(i).setLevel("VIP");
        }else if (tmp > 3){
            customersList.get(i).setLevel("Regular");
        }else {
            customersList.get(i).setLevel("Guest");
        }
    }


    public static String generateID(){
        String ID = "";
        if(customersList.size() < 9) {
            ID = "C" + "00" + (customersList.size() + 1);
        } else if (customersList.size() >= 9 && customersList.size() <= 99) {
            ID = "C" + "0" + (customersList.size()+1);

        } else if (customersList.size() >= 100) {
            ID = "C" + (customersList.size()+1);
        }
        return ID;
    }

    public static boolean isExist(String str) {
        for (Customer customer : customersList) {
            if (customer.getUsername().matches(str)) {
                return true;
            }
        }
        return false;
    }






}
