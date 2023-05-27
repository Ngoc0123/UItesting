package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.Item;
import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Classes.ManageItem;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;


public class AdminController implements Initializable {

    @FXML
    private ListView<String> list;
    private ArrayList<Customer> customers;
    private ArrayList<Item> items;

    @FXML
    private Button CancelButton;

    @FXML
    private Text Customertext;

    @FXML
    private Button DeleButton;

    @FXML
    private Button EditButton;

    @FXML
    private Label ErrorMess;

    @FXML
    private Pane ErrorPane;

    @FXML
    private Text Etex1;

    @FXML
    private TextField Etex10;

    @FXML
    private TextField Etex11;

    @FXML
    private TextField Etex12;

    @FXML
    private TextField Etex13;

    @FXML
    private TextField Etex14;

    @FXML
    private Text Etex2;

    @FXML
    private Text Etex3;

    @FXML
    private Text Etex4;

    @FXML
    private Text Etex5;

    @FXML
    private Text Etex6;

    @FXML
    private Text Etex7;

    @FXML
    private TextField Etex8;

    @FXML
    private TextField Etex9;

    @FXML
    private Text Itemtext;

    @FXML
    private Button UkiButton;

    @FXML
    private Pane editPane;

    @FXML
    private Pane information;

    @FXML
    private Text tex1;

    @FXML
    private Text tex10;

    @FXML
    private Text tex11;

    @FXML
    private Text tex12;

    @FXML
    private Text tex13;

    @FXML
    private Text tex14;

    @FXML
    private Text tex15;

    @FXML
    private Text tex16;

    @FXML
    private Text tex2;

    @FXML
    private Text tex3;

    @FXML
    private Text tex4;

    @FXML
    private Text tex5;

    @FXML
    private Text tex6;

    @FXML
    private Text tex7;

    @FXML
    private Text tex8;

    @FXML
    private Text tex9;

    @FXML
    private Button update;

    private String chosenID;
    private String chosenID2;
    private int choseIndex;





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


        //Print array
        onItemP();

        //avoid spamming the function
        Itemtext.setDisable(true);
        Customertext.setDisable(false);
        information.setVisible(false);

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


        //avoid spamming the function
        Itemtext.setDisable(false);
        Customertext.setDisable(true);
        information.setVisible(false);

        //when click the content in the table view list
        list.getSelectionModel().selectedItemProperty().addListener(this::whenItemSelected);

    }

    private void onItemSelect(String str){
        tex1.setText("ID:");
        tex2.setText("Title:");
        tex3.setText("Genre:");
        tex4.setText("Rental Type:");
        tex5.setText("Loan Type:");
        tex6.setText("Stock");
        tex7.setText("Rental Fee:");
        tex8.setText("Rental Status:");

        for(Item item : ManageItem.items) {
            if (str.matches(item.getId())) {
                tex9.setText(item.getId());
                tex10.setText(item.getTitle());
                tex11.setText(item.getGenre());
                tex12.setText(item.getRentalType());
                tex13.setText(item.getLoanType());
                tex14.setText("" + item.getStock());
                tex15.setText("" + item.getRentalFee());
                tex16.setText("Null");

            }
        }



    }

    private void onCusSelect(String str){

        tex1.setText("ID:");
        tex2.setText("Name:");
        tex3.setText("Address:");
        tex4.setText("Phone:");
        tex5.setText("User Name");
        tex6.setText("Password:");
        tex7.setText("List of rentals:");
        tex8.setVisible(false);


        for(Customer cus : ManageCustomer.customersList) {
            if (str.matches(cus.getId())) {
                tex9.setText(cus.getId());
                tex10.setText(cus.getName());
                tex11.setText(cus.getAddress());
                tex12.setText(cus.getPhone());
                tex13.setText(cus.getUsername());
                tex14.setText("" + cus.getPassword());
                tex15.setText("" + cus.getRentals());
                tex16.setVisible(false);
            }
        }


    }

    @FXML
    private void onDeleButton(){
        ManageItem.items.remove(choseIndex);
        list.getItems().clear();
    }

    //Print Item ID, name
    private void onItemP() {
        for (int i = 0; i < items.size(); i++) {
            list.getItems().addAll(items.get(i).getId());
        }
    }

    //Print Customer ID, name
    private  void onCusP() {
        for (int i = 0; i < customers.size(); i++) {
            list.getItems().addAll(customers.get(i).getId());

        }

    }


    //When Select content in List View
    protected void whenItemSelected(ObservableValue<? extends String> Observable, String str, String str2){
        choseIndex = list.getSelectionModel().getSelectedIndex();
        chosenID2 = list.getSelectionModel().getSelectedItem();
        information.setVisible(true);


        if (Customertext.isDisable()){
            DeleButton.setVisible(false);
            chosenID = "C001";
            if(chosenID2 == null) {
                onCusSelect(chosenID);
            }else {
                chosenID2 = list.getSelectionModel().getSelectedItem();
                onCusSelect(chosenID2);
            }
        }

        if (Itemtext.isDisable()){
            DeleButton.setVisible(true);
            chosenID = "I001-2001";
            if(chosenID2 == null) {
                onItemSelect(chosenID);

            }else {
                chosenID2 = list.getSelectionModel().getSelectedItem();
                onItemSelect(chosenID2);
            }

        }


    }
    @FXML
    private void onEditButton(){};
    @FXML
    private void onUpdateButton(){};

    @FXML
    private void CancelButtonPressed(){};

    @FXML
    private void ukiButton(){};








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.customers = ManageCustomer.customersList;
    }
}
