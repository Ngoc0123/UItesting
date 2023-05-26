package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.Item;
import com.example.oop_ui_test.Classes.ManageCustomer;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;


public class AdminController implements Initializable {
    @FXML
    private Button DeleButton;

    @FXML
    private Button EditButton;
    @FXML
    private Text Itemtext;

    @FXML
    private Text Customertext;

    @FXML
    private ListView<String> list;
    private ArrayList<Customer> customers;
    private ArrayList<Item> items;

    private  static Scanner x;




    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @FXML
    private void ItemEnter(MouseEvent event) {
        Itemtext.setUnderline(true);
    }

    @FXML
    private void ItemExit(MouseEvent event) {
        Itemtext.setUnderline(false);
    }

    @FXML
    private void CustomerEnter(MouseEvent event) {
        Customertext.setUnderline(true);
    }

    @FXML
    private void CustomerExit(MouseEvent event) {
        Customertext.setUnderline(false);
    }

    @FXML
    private void listOnClick(MouseEvent event){
        list.setOnMouseClicked(event1 -> {
            String selectedItem = list.getSelectionModel().getSelectedItem().toString();
        });
    }




    @FXML
    private void onItemPress(MouseEvent event) { //on click Item text

        items = new ArrayList<Item>();

        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Scanner fileScanner = null;
        //determine the path of the file need to read
        try {
            fileScanner = new Scanner(new FileReader(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //break the file into string each after ","
        while (fileScanner.hasNextLine()) {
             Item item = new Item();
            String line = fileScanner.nextLine();
            StringTokenizer inReader = new StringTokenizer(line, ",");

            item.setId(inReader.nextToken());
            item.setTitle(inReader.nextToken());

            //add result to the array
            items.add(item);
        }
        //refresh the View List each time change tab
        list.getItems().clear();

        //set button disable for each time change tab
        DeleButton.setDisable(true);
        EditButton.setDisable(true);

        //Print array
        onItemP();

        //avoid spamming the function
        Itemtext.setDisable(true);
        Customertext.setDisable(false);

        //when click the content in the table view list
        list.getSelectionModel().selectedItemProperty().addListener(this::whenItemSelected);


    }

    @FXML
    private void onCustomerPress(MouseEvent event) {//on click customer text

        //refresh the View List each time change tab
        list.getItems().clear();

        //Print Customer ID, name
        onCusP();
        //set button disable for each time change tab
        DeleButton.setDisable(true);
        EditButton.setDisable(true);

        //avoid spamming the function
        Itemtext.setDisable(false);
        Customertext.setDisable(true);

        //when click the content in the table view list
        list.getSelectionModel().selectedItemProperty().addListener(this::whenItemSelected);

    }

    @FXML
    private void onDeleButton(){
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        String ID="C008";
        for (int i = 0; i < customers.size();i++){
            System.out.println(customers.get(i).getId() + "" + customers.get(i).getName());
        }

        deleteFunction(path.toString() + "\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt", ID);







    }





    //Print Item ID, name
    private void onItemP() {
        for (int i = 0; i < items.size(); i++) {
            list.getItems().addAll(items.get(i).getId() + " " + items.get(i).getTitle());
        }
    }

    //When Select content in List View
    protected void whenItemSelected(ObservableValue<? extends String> Observable, String str, String str2){
        ObservableList<String> selectItem = list.getSelectionModel().getSelectedItems();

        DeleButton.setDisable(false);
        EditButton.setDisable(false);
    }


    //Print Customer ID, name
    private  void onCusP() {
        for (int i = 0; i < customers.size(); i++) {
            list.getItems().addAll(customers.get(i).getId() + " " + customers.get(i).getUsername());

        }

    }
    
    private void deleteFunction(String filepath, String removeTerm){

        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String ID = ""; String name = ""; String address = ""; String phone ="";
               String rentalNumber = "" ; String level = ""; String userName =""; String passWord ="";


        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                ID = x.next();
                name = x.next();
                address = x.next();
                phone = x.next();
                rentalNumber = x.next();
                level = x.next();
                userName = x.next();
                passWord = x.next();
                if(!ID.equals(removeTerm)){
                    pw.println(ID + "," + name + "," + address+ ","
                            + phone + "," + rentalNumber + "," + level + "," + userName + "," + passWord);
                }

            }
            x.close();

            pw.flush();
            pw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

        }catch (Exception e){
            System.out.println(e);
            
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.customers = ManageCustomer.customersList;
    }
}
