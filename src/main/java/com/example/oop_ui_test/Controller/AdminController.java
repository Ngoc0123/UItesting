package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    private ArrayList<Rental> rental;

    @FXML
    private Button CancelButton;

    @FXML
    private Text Customertext;

    @FXML
    private ListView<String> RentalDetail;

    @FXML
    private Button DeleButton;

    @FXML
    private Button EditButton;

    @FXML
    private Label ErrorMess;

    @FXML
    private Pane ErrorPane;

    @FXML
    private Text Etex0;

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
    private TextField Etex15;

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

    @FXML
    private ChoiceBox<String> GenreBox;
    private final String[] Genreword = {"Action", "Horror", "Drama", "Comedy"};

    @FXML
    private ChoiceBox<String> loanTypeBox;

    private final String[] LoanTypeword = {"1-week loan", "2-days loan"};

    @FXML
    private ChoiceBox<String> renTalType;

    private final String[] rentalType ={"Old Record", "DVD", "Game"};

    private String chosenID;
    private String chosenID2;
    private int choseIndex;
//    private Customer customer;





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
        RentalDetail.setVisible(false);
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
                RentalDetail.setVisible(true);

//                RentalDetail.getItems().addAll(retal.toString());

                tex15.setVisible(false);
                tex16.setVisible(false);
            }
        }


    }

    @FXML
    private void onDeleButton(){
        ManageItem.items.remove(choseIndex);
        list.getItems().remove(choseIndex);
        ManageItem.saveFile();
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
        RentalDetail.getItems().clear();


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
    private void onEditButton(){
        editPane.setVisible(true);
        if(Customertext.isDisable()) {
            loanTypeBox.setVisible(false);
            renTalType.setVisible(false);
            Etex10.setVisible(true);
            Etex11.setVisible(true);
            Etex1.setText("ID:");
            Etex2.setText("Name:");
            Etex3.setText("Address:");
            Etex4.setText("Phone:");
            Etex5.setText("User Name");
            Etex6.setText("Password:");
            Etex7.setVisible(false);

            Etex8.setText(customers.get(choseIndex).getId());
            for (Customer cus : ManageCustomer.customersList) {
                if (chosenID2.matches(cus.getId())) {
                    Etex9.setPromptText(cus.getName());
                    Etex10.setPromptText(cus.getAddress());
                    Etex11.setPromptText(cus.getPhone());
                    Etex12.setPromptText(cus.getUsername());
                    Etex13.setPromptText("" + cus.getPassword());
                    Etex14.setVisible(false);

                }
            }
        }
        if(Itemtext.isDisable()){
            loanTypeBox.setVisible(true);
            renTalType.setVisible(true);

            Etex0.setText("ID");
            Etex1.setText("Title:");
            Etex2.setText("Genre:");
            Etex3.setText("Rental Type:");
            Etex4.setText("Loan Type:");
            Etex5.setText("Stock");
            Etex6.setText("Rental Fee:");
            Etex7.setText("Rental Status");

            Etex8.setText(items.get(choseIndex).getId());
            for (Item item : ManageItem.items) {
                if (chosenID2.matches(item.getId())) {
                    Etex8.setPromptText(item.getId());
                    Etex9.setPromptText(item.getTitle());
                    Etex10.setVisible(false);
                    Etex11.setVisible(false);
                    Etex12.setPromptText(""+item.getStock());
                    Etex13.setPromptText("" + item.getRentalFee());
                    Etex14.setPromptText("null");
                    Etex15.setPromptText("null");

                }
            }
        }

        }


    @FXML
    private void onUpdateButton(){

        if(Customertext.isDisable()) {
            updateCustomer();
        } else if (Itemtext.isDisable()) {
            updateItem();
        }

    };

    @FXML
    private void CancelButtonPressed(){editPane.setVisible(false);}

    @FXML
    private void ukiButton(){ErrorPane.setVisible(false);}

    private void updateCustomer(){
        Customer cus = new Customer();
        Rental rental1 = new Rental();

        if (Etex9.getText() == null || Etex9.getText().length() < 1) {
            ErrorMess.setText("Please enter your name");
            ErrorPane.setVisible(true);
            return;
        }

        if (Etex10.getText() == null || Etex10.getText().length() < 1) {
            ErrorMess.setText("Please enter your address");
            ErrorPane.setVisible(true);
            return;
        }
        if(renTalType.getValue().equals("Game")){
            Etex3.setVisible(false);
            GenreBox.setVisible(false);
        }



        if (!checkNumber(Etex11.getText()) || Etex11.getText() == null ||Etex11.getText().length() != 10) {
            ErrorMess.setText("Invalid Phone number! Please enter 10 digit numbers: ");
            ErrorPane.setVisible(true);
            return;
        }

        if (Etex12.getText() == null || Etex12.getText().length() < 1) {
            ErrorMess.setText("Please enter User Name");
            return;
        } else if (ManageCustomer.isExist(Etex12.getText())) {
            ErrorMess.setText("This User Name is already exist, please enter a new one");
            ErrorPane.setVisible(true);
            return;
        }

        if (Etex13.getText() == null || Etex13.getText().length() < 8) {
            ErrorMess.setText("Wrong format!! Password must have 8 or more characters. Please enter Password");
            ErrorPane.setVisible(true);
            return;
        }
        ManageCustomer.readFile();
        cus.setId(Etex8.getText());
        cus.setName(Etex9.getText());
        cus.setAddress(Etex10.getText());
        cus.setPhone(Etex11.getText());
        cus.setUsername(Etex12.getText());
        cus.setPassword(Etex13.getText());
        //
        //RENTAL HERE
        //
        //


        ManageCustomer.customersList.set(choseIndex,cus);
        ManageCustomer.saveFile();


        Etex9.setText("");
        Etex10.setText("");
        Etex11.setText("");
        Etex12.setText("");
        Etex13.setText("");

        editPane.setVisible(false);

    }

    private void updateItem(){
        Item item = new Item();



        if (Etex9.getText() == null || Etex9.getText().length() < 1) {
            ErrorMess.setText("Please enter Item name");
            ErrorPane.setVisible(true);
            return;
        }
        System.out.println(item.getRentalType());
        System.out.println(item.getGenre());
        System.out.println(item.getLoanType());




        if (Etex12.getText() == null|| !checkNumber(Etex12.getText())) {
            ErrorMess.setText("Please insert quantity of the Item");
            return;
        }

        if (Etex13.getText() == null|| !checkNumberDouble(Etex13.getText())) {
            ErrorMess.setText("Missing Price, Please insert price of the Item");
            ErrorPane.setVisible(true);
            return;
        }
        item.setId(Etex8.getText());
        item.setTitle(Etex9.getText());
        item.setGenre(GenreBox.getValue());
        item.setRentalType(renTalType.getValue());
        item.setLoanType(loanTypeBox.getValue());
        item.setStock(Integer.parseInt(Etex12.getText()));
        item.setRentalFee(Double.parseDouble(Etex13.getText()));

        ManageItem.items.set(choseIndex,item);
        ManageItem.saveFile();


        Etex9.setText("");
        Etex13.setText("");

        editPane.setVisible(false);

    }

    private boolean checkNumber(String str){
        try{
            int input = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }
    }private boolean checkNumberDouble(String str){
        try{
            double input = Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.customers = ManageCustomer.customersList;

        GenreBox.getItems().addAll(Genreword);
        loanTypeBox.getItems().addAll(LoanTypeword);
        renTalType.getItems().addAll(rentalType);

        GenreBox.setOnAction(this::getGenreBox);
        loanTypeBox.setOnAction(this::getLoanType);
        renTalType.setOnAction(this::getRentalType);


    }
    private void getGenreBox(ActionEvent event){
        Item newItem = new Item();
        String GenreGet = GenreBox.getValue();
        newItem.setGenre(GenreGet);

    }
    private void getRentalType(ActionEvent event){
        String rentalTypeGet = renTalType.getValue();
    }
    private void getLoanType(ActionEvent event){
        String loanTypeGet = loanTypeBox.getValue();
    }

}
