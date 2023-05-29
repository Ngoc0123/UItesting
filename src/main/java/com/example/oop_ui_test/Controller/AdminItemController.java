package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.*;
import com.example.oop_ui_test.Main;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;


public class AdminItemController extends Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ListView<String> list;
    private ArrayList<Customer> customers;
    private ArrayList<Item> items;


    private String chosenGenreBox;
    private String chosenRentalBox;
    private String chosenLoanBox;

    @FXML
    private Button CancelButton;

    @FXML
    private Text Customertext;

    @FXML
    private ChoiceBox<String> getGenreBox;

    @FXML
    private TextField getID;

    @FXML
    private TextField getYearID;


    @FXML
    private ChoiceBox<String> getLoanTypeBox;

    @FXML
    private TextField getQuantity;

    @FXML
    private TextField getRentalFee;

    @FXML
    private ChoiceBox<String > getRentalTypeBox;

    @FXML
    private TextField getTitle;

    @FXML
    private Button RegisterButton;

    @FXML
    private Pane RegisPane;



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
    private Button RegisCancelButton;

    @FXML
    private TextField sreachBar;

    @FXML
    private Label RegisMessError;

    @FXML
    private  Pane RegisPaneError;

    @FXML
    private Button addButton;

    @FXML
    private Button RegisOkayButton;
    @FXML
    private TextField searchBar;

    @FXML
    private Text searchErrorText;

    @FXML
    private RadioButton sortByID;

    @FXML
    private RadioButton sortByTitle;

    @FXML
    private RadioButton sortOutOfStock;

    @FXML
    private Text logOutText;

    @FXML
    private ChoiceBox<String> GenreBox;
    private final String[] Genreword = {"Action", "Horror", "Drama", "Comedy"};


    @FXML
    private ChoiceBox<String> loanTypeBox;

    private final String[] LoanTypeword = {"1-week loan", "2-day loan"};

    @FXML
    private ChoiceBox<String> renTalType;

    private final String[] rentalType ={"Record", "DVD", "Game"};

    private String chosenID;
    private String chosenID2;
    private int choseIndex;
//    private Customer customer;

    String Iid = null, IidYear=null, Ititle = null, Igenre = null, IrentalType= null,
    IloanType = null, Istock = null, IrentalFee = null, Idtrue = null;





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
    private  void addButton(){RegisPane.setVisible(true);};

    @FXML
    private void onRegisOkayButton(){RegisPaneError.setVisible(false);}

    @FXML
    private void onRegisCancelButton(){RegisPane.setVisible(false);}

    @FXML
    void logOutEnter(MouseEvent event) {
        logOutText.setUnderline(true);
    }

    @FXML
    void logOutExit(MouseEvent event) {
        logOutText.setUnderline(false);
    }


    @FXML
    void onLogOutAction(MouseEvent event) throws IOException {
        ManageItem.readFile();
        ManageCustomer.readFile();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        root = loader.load();

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void searchBarEnter(ActionEvent event) {
        search(searchBar.getText());
    }

    @FXML
    void searchBarPress(MouseEvent event) {
        search(searchBar.getText());
    }

    @FXML
    void onRefresh(ActionEvent event) {
        ManageItem.sort(ManageItem.SORT_BY_ID);
        onRefreshList();
        searchErrorText.setText("");
        sortByID.setSelected(false);
        sortByTitle.setSelected(false);
    }


    public void setRadio1() {
        ManageItem.sort(ManageItem.SORT_BY_NAME);
        onRefreshList();
        sortByID.setSelected(false);
        sortOutOfStock.setSelected(false);
    }
    public void setRadio2() {
        ManageItem.sort(ManageItem.SORT_BY_ID);
        onRefreshList();
        sortByTitle.setSelected(false);
        sortOutOfStock.setSelected(false);
    }

    public void setRadio3(){
        ManageItem.sort(ManageItem.SORT_BY_ID);
        list.getItems().clear();
        for(Item item: ManageItem.items){
            if(item.getStock() == 0){
                list.getItems().add(item.getId());
            }
        }
        sortByID.setSelected(false);
        sortByTitle.setSelected(false);
    }

    public void search(String input){
        list.getItems().clear();
        int matches = 0;
        ArrayList<String> lists = new ArrayList<String>();
        for(int i = 1; i<= ManageItem.items.size();i++){
            if(ManageItem.items.get(i-1).getTitle().toLowerCase().matches(".*"+input.toLowerCase()+".*")||ManageItem.items.get(i-1).getId().toLowerCase().matches(".*"+input)){
                matches++;
                searchErrorText.setText("");
                lists.add(ManageItem.items.get(i-1).getId());

            }
        }
        list.getItems().addAll(lists);

        if(matches == 0){
            searchErrorText.setText("There is no Item match with your search!! Please enter another keyword.");
            onRefreshList();
        }
    }
    @FXML
    private void onCustomerPress(MouseEvent event) throws IOException {//on click customer text

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminCusView.fxml"));
        root = loader.load();

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    private void onItemSelect(String str){
        System.out.println(str);
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
                if(item.getStock() > 0) {
                    tex16.setText("Available");
                }else{
                    tex16.setText("Out of stock");
                }
                tex8.setVisible(true);
                tex15.setVisible(true);
                tex16.setVisible(true);

            }
        }



    }


    @FXML
    private void onDeleButton() {
        choseIndex = list.getSelectionModel().getSelectedIndex();

        for(Customer customer: ManageCustomer.customersList){
            ArrayList<Rental> rentals = new ArrayList<Rental>();
            for(Rental rental : customer.getRentals()){
                if(rental.getId().matches(ManageItem.items.get(choseIndex).getId())){
                    rentals.add(rental);
                }
            }
            customer.getRentals().removeAll(rentals);
        }

        ManageItem.items.remove(choseIndex);
        ManageItem.saveFile();
        ManageCustomer.saveFile();
        onRefreshList();



    }

    //Print Item ID, name
    private void onRefreshList() {
        list.getItems().clear();
        for (Item item : ManageItem.items) {
            list.getItems().addAll(item.getId());
        }
    }


    //When Select content in List View
    protected void whenItemSelected(ObservableValue<? extends String> Observable, String str, String str2){
        choseIndex = list.getSelectionModel().getSelectedIndex();
        chosenID2 = list.getSelectionModel().getSelectedItem();
        chosenGenreBox = GenreBox.getSelectionModel().getSelectedItem();
        chosenLoanBox = loanTypeBox.getSelectionModel().getSelectedItem();
        chosenRentalBox = renTalType.getSelectionModel().getSelectedItem();

        information.setVisible(true);

            DeleButton.setVisible(true);
            chosenID = "I001-2001";
            if(chosenID2 == null) {
                onItemSelect(chosenID);

            }else {
                chosenID2 = list.getSelectionModel().getSelectedItem();
                onItemSelect(chosenID2);
            }




    }
    @FXML
    private void onEditButton(){

            editPane.setVisible(true);
            loanTypeBox.setVisible(true);
            renTalType.setVisible(true);

            Etex0.setText("ID");
            Etex1.setText("Title:");
            Etex2.setText("Genre:");
            Etex3.setText("Rental Type:");
            Etex4.setText("Loan Type:");
            Etex5.setText("Stock");
            Etex6.setText("Rental Fee:");
            Etex7.setVisible(false);

            for(Item item : ManageItem.items) {
                if(chosenID2.matches(item.getId())){
                    Etex8.setText(item.getId());
                    choseIndex = ManageItem.items.indexOf(item);
                }
            }

            System.out.println(Etex8.getText());

            for (Item item : ManageItem.items) {
                if (chosenID2.matches(item.getId())) {
                    Etex8.setText(item.getId());
                    Etex9.setText(item.getTitle());
                    GenreBox.setValue(item.getGenre());
                    renTalType.setValue(item.getRentalType());
                    loanTypeBox.setValue(item.getLoanType());
                    Etex10.setVisible(false);
                    Etex11.setVisible(false);
                    Etex12.setVisible(false);
                    Etex13.setText("" + item.getStock());
                    Etex14.setText(""+item.getRentalFee());
                    Etex15.setVisible(false);

                }
            }

        }


    @FXML
    private void onUpdateButton(){
        updateItem();
    }

    @FXML
    private void CancelButtonPressed(){editPane.setVisible(false);}

    @FXML
    private void ukiButton(){ErrorPane.setVisible(false);}

    private void updateItem(){
        Item item = ManageItem.items.get(choseIndex);
        GenreBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> chosenGenreBox = newValue);

        loanTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> chosenLoanBox = newValue);

        renTalType.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> chosenRentalBox = newValue);



        if (Etex9.getText() == null || Etex9.getText().length() < 1) {
            ErrorMess.setText("Please enter Item name");
            ErrorPane.setVisible(true);
            return;
        }

        if(chosenRentalBox.equals("Game")){
            GenreBox.setValue(null);
        }


        if (!checkNumber(Etex13.getText())||Etex13.getText() == null) {
            ErrorMess.setText("Please insert quantity of the Item");
            System.out.println(Etex12.getText());
            ErrorPane.setVisible(true);
            return;
        }

        if (Etex14.getText() == null|| !checkNumberDouble(Etex14.getText())) {
            ErrorMess.setText("Missing Price, Please insert price of the Item");
            ErrorPane.setVisible(true);
            return;
        }
        item.setId(Etex8.getText());
        item.setTitle(Etex9.getText());
        item.setGenre(chosenGenreBox);
        item.setRentalType(chosenRentalBox);
        item.setLoanType(chosenLoanBox);
        item.setStock(Integer.parseInt(Etex13.getText()));
        item.setRentalFee(Double.parseDouble(Etex14.getText()));

        ManageItem.items.set(choseIndex,item);
        ManageItem.saveFile();


        Etex9.setText("");
        Etex13.setText("");

        editPane.setVisible(false);

    }

    @FXML
    private void onRegisItemButton(){
        getGenreBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> Igenre = newValue);

        getRentalTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IrentalType = newValue);

        getLoanTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IloanType = newValue);

        //set default value


        String tmp = getID.getText();
        if (!checkNumber(tmp)||tmp == null||tmp.length() > 3) {
            RegisMessError.setText("ID is 3 unique number, please enter again");
            RegisPaneError.setVisible(true);
            return;
        }
        if(tmp.length() == 1){
            Iid = "I"+"00" +tmp;
            System.out.println(Iid);

        }else if(tmp.length() ==2){
            Iid = "I"+"0" + tmp;
            System.out.println(Iid);
        }else  {
            Iid = "I"+tmp;
            System.out.println(Iid);
        }
        if((ManageItem.isExist((Iid)))){
            RegisMessError.setText("The ID is already exist, try another one");
            RegisPaneError.setVisible(true);
            return;
        }

        tmp = getYearID.getText();
        if(!checkNumber(tmp) || tmp == null || tmp.length() != 4 ){
            RegisMessError.setText("Please enter Year of Item follow the format YYYY");
            RegisPaneError.setVisible(true);
            return;
        }
        IidYear = tmp;

        Idtrue = Iid + "-" + IidYear;



        tmp = getTitle.getText();
        if(tmp == null|| tmp.length() < 1){
            RegisMessError.setText("Please enter Item Title");
            RegisPaneError.setVisible(true);
            return;
        }
        Ititle = tmp;

        if(getGenreBox.getValue() == null){
            RegisPaneError.setVisible(true);
            RegisMessError.setText("You forgot to choose Genre Type, please choose it again");
            return;
        }
        if(getRentalTypeBox.getValue() == null){
            RegisPaneError.setVisible(true);
            RegisMessError.setText("You forgot to choose RENTAL Type, please choose it again");
            return;
        }

        if(getLoanTypeBox.getValue() == null){
            RegisPaneError.setVisible(true);
            RegisMessError.setText("You forgot to choose Loan Type, please choose it again");
            return;
        }

        if(getRentalTypeBox.getValue().equals("Game")){
            getGenreBox.setValue(null);
        }



        tmp = getQuantity.getText();
        if(!checkNumber(tmp) || tmp == null || Integer.parseInt(tmp) < 0){
            RegisMessError.setText("Wrong format! Please enter Item Qantity again");
            RegisPaneError.setVisible(true);
            return;
        }
        Istock = tmp;

        tmp = getRentalFee.getText();
        if(!checkNumberDouble(tmp)|| tmp== null){
            RegisMessError.setText("Please enter Item Price");
            RegisPaneError.setVisible(true);
            return;
        }
        IrentalFee = tmp;
        createItem();

        RegisPane.setVisible(false);

        getID.setText("");
        getYearID.setText("");
        getTitle.setText("");
        getQuantity.setText("");
        getRentalFee.setText("");

    }

    private void createItem(){
        Item newItem = new Item();
        newItem.setId(Idtrue);
        newItem.setTitle(Ititle);
        newItem.setGenre(Igenre);
        newItem.setRentalType(IrentalType);
        newItem.setLoanType(IloanType);
        newItem.setStock(Integer.parseInt(Istock));
        newItem.setRentalFee(Double.parseDouble(IrentalFee));
        ManageItem.items.add(newItem);
        ManageItem.saveFile();

    }

    private boolean checkNumber(String str){
        try{
            int input = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }
    }
    private boolean checkNumberDouble(String str){
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

        getGenreBox.getItems().addAll(Genreword);
        getGenreBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> Igenre = newValue);
        getLoanTypeBox.getItems().addAll(LoanTypeword);
        getRentalTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IrentalType = newValue);
        getRentalTypeBox.getItems().addAll(rentalType);
        getLoanTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IloanType = newValue);

        GenreBox.getItems().addAll(Genreword);
        GenreBox.setValue("Action");
        loanTypeBox.getItems().addAll(LoanTypeword);
        loanTypeBox.setValue("2-day loan");
        renTalType.getItems().addAll(rentalType);
        renTalType.setValue("DVD");

        list.getSelectionModel().selectedItemProperty().addListener(this::whenItemSelected);
        onRefreshList();


    }





}
