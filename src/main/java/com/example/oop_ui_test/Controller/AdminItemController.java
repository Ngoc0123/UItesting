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


public class AdminItemController implements Initializable {
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
        onRefreshList();

        //avoid spamming the function

        Itemtext.setDisable(true);
        Customertext.setDisable(false);
        information.setVisible(false);

        //when click the content in the table view list
        list.getSelectionModel().selectedItemProperty().addListener(this::whenItemSelected);


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

                choseIndex = ManageCustomer.customersList.indexOf(cus);


                tex15.setVisible(false);
                tex16.setVisible(false);
                return;
            }
        }


    }

    @FXML
    private void onDeleButton() {
        choseIndex = list.getSelectionModel().getSelectedIndex();
        list.getItems().remove(choseIndex);
        for(Customer customer: ManageCustomer.customersList){
            ArrayList<Rental> rentals = new ArrayList<Rental>();
            for(Rental rental : customer.getRentals()){
                if(rental.getId().matches(ManageItem.items.get(choseIndex).getId())){
                    rentals.add(rental);
                }
            }
            customer.getRentals().removeAll(rentals);
        }

        ManageItem.items.remove(choseIndex+1);
        ManageItem.saveFile();
        ManageCustomer.saveFile();



    }

    //Print Item ID, name
    private void onRefreshList() {

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
        chosenGenreBox = GenreBox.getSelectionModel().getSelectedItem();
        chosenLoanBox = loanTypeBox.getSelectionModel().getSelectedItem();
        chosenRentalBox = renTalType.getSelectionModel().getSelectedItem();

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
            Etex7.setVisible(false);

            Etex8.setText(items.get(choseIndex).getId());
            for (Item item : ManageItem.items) {
                if (chosenID2.matches(item.getId())) {
                    Etex8.setPromptText(item.getId());
                    Etex9.setPromptText(item.getTitle());
                    GenreBox.setValue(item.getGenre());
                    renTalType.setValue(item.getRentalType());
                    loanTypeBox.setValue(item.getLoanType());
                    Etex10.setVisible(false);
                    Etex11.setVisible(false);
                    Etex12.setVisible(false);
                    Etex13.setPromptText("" + item.getStock());
                    Etex14.setPromptText(""+item.getRentalFee());
                    Etex15.setVisible(false);

                }
            }
        }

        GenreBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s==null)? "Nothing Selected":s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });

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
            RegisMessError.setText("You forgot to choose Genre Type, please choose it agian");
            return;
        }
        if(getRentalTypeBox.getValue() == null){
            RegisPaneError.setVisible(true);
            RegisMessError.setText("You forgot to choose RENTAL Type, please choose it agian");
            return;
        }

        if(getLoanTypeBox.getValue() == null){
            RegisPaneError.setVisible(true);
            RegisMessError.setText("You forgot to choose Loan Type, please choose it agian");
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

        getGenreBox.getItems().addAll(Genreword);
        getGenreBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> Igenre = newValue);
        getLoanTypeBox.getItems().addAll(LoanTypeword);
        getRentalTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IrentalType = newValue);
        getRentalTypeBox.getItems().addAll(rentalType);
        getLoanTypeBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> IloanType = newValue);

        GenreBox.getItems().addAll(Genreword);
        GenreBox.setValue("Action");
        loanTypeBox.getItems().addAll(LoanTypeword);
        loanTypeBox.setValue("2-days loan");
        renTalType.getItems().addAll(rentalType);
        renTalType.setValue("DVD");





    }





}
