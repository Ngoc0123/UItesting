package com.example.oop_ui_test.Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ManageCustomer {
    private static ManageCustomer manageCustomerObj;
    public static ArrayList<Customer> customersList = new ArrayList<Customer>();

    public static ManageCustomer getInstance(){
        if(manageCustomerObj == null){
            manageCustomerObj = new ManageCustomer();
        }
        return manageCustomerObj;
    }


    //Function to read customersList from customer.txt
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
            if (inReader.countTokens() == 8) {
                customer.setId(inReader.nextToken());
                customer.setName(inReader.nextToken());
                customer.setAddress(inReader.nextToken());
                customer.setPhone(inReader.nextToken());

                customer.setLevel(inReader.nextToken());

                customer.setUsername(inReader.nextToken());
                customer.setPassword(inReader.nextToken());
                customer.setRewardPoint(Integer.parseInt(inReader.nextToken()));

                customersList.add(customer);
            } else if(inReader.countTokens() == 2){
                Rental newRental = new Rental();
                newRental.setId(inReader.nextToken());
                newRental.setStatus(inReader.nextToken());
                customersList.get(customersList.size()-1).getRentals().add(newRental);
                customersList.get(customersList.size()-1).setRentalNumber(customersList.get(customersList.size()-1).getRentalNumber()+1);
                if(newRental.getStatus().matches("Returned")){
                    customersList.get(customersList.size()-1).setReturned(customersList.get(customersList.size()-1).getReturned()+1);
                }
            }
        }


    }

    //Function to save customersList to customer.txt
    public  static void saveFile(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        sort(SORT_BY_ID);

            try {
                FileWriter file = new FileWriter(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\customers.txt");

                for(Customer customer: customersList) {
                    file.write(customer.getId() + "," + customer.getName() + "," + customer.getAddress() + "," + customer.getPhone() + "," + customer.getLevel()
                        + "," + customer.getUsername() + "," + customer.getPassword()+","+customer.getRewardPoint()+"\n");
                    for(Rental rental: customer.getRentals()){
                        file.write(rental.getId()+","+rental.getStatus()+"\n");
                    }
                }

                file.close();


            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

    }

    //Function to auto update level of customer if meet requirement
    public static void updateLevel(int i){
        int tmp = customersList.get(i).getReturned();

        if(tmp > 5 || customersList.get(i).getLevel().matches("VIP")){
            customersList.get(i).setLevel("VIP");
        }else if (tmp > 3 || customersList.get(i).getLevel().matches("Regular")){
            customersList.get(i).setLevel("Regular");
        }else {
            customersList.get(i).setLevel("Guest");
        }
    }

    //find index of a customer using id
    public static int find(String id){
        for(Customer cus: customersList){
            if(cus.getId().matches(id)){
                return customersList.indexOf(cus);
            }
        }
        return -1;
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

    public static boolean SORT_BY_NAME = true;
    public static boolean SORT_BY_ID = false;

    public static void sort(boolean type){
        if(type)
            customersList.sort(new Comparator<Customer>() {
                @Override
                public int compare(Customer a, Customer b) {
                    return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
                }
            });
        else
            customersList.sort(new Comparator<Customer>() {
                @Override
                public int compare(Customer a, Customer b) {
                    return a.getId().compareTo(b.getId());
                }
            });
    }

    public static boolean checkNumber(String str) {
        try {
            int input = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }




}
