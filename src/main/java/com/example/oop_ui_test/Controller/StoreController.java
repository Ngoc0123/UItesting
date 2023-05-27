package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.*;
import com.example.oop_ui_test.Listener;
import com.example.oop_ui_test.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class StoreController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int cusIndex;
    private String chosenId;
    private ArrayList<Item> items;
    @FXML
    private Label myAccountLabel;



    @FXML
    private VBox chosenBox;

    @FXML
    private Text chosenName;
    @FXML
    private Text rentalsText;

    @FXML
    private ImageView chosenPic;

    @FXML
    private Text chosenPrice;

    @FXML
    private Text chosenStock;

    @FXML
    private Text chosenLoanType;

    @FXML
    private Text chosenRentalType;
    @FXML
    private Text errorText = new Text();


    public StoreController() {
        items = new ArrayList<Item>();
    }

    public void setCusIndex(int cusIndex) {
        this.cusIndex = cusIndex;
    }

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchbar;

    @FXML
    private Button rentingButton;
    private Listener listener;


    private void setChosenItem(Item item){

        chosenId = item.getId();
        chosenName.setText(item.getTitle());
        errorText.setText("");
        if(stringCompare(item.getRentalType(),"Record") == 0 ||stringCompare(item.getRentalType(),"DVD") == 0){
            chosenRentalType.setText("Rental type: "+item.getRentalType()+ "\t\tGenre: "+item.getGenre());
        }else{
            chosenRentalType.setText("");
        }
        chosenLoanType.setText("Rental type: "+item.getLoanType());
        chosenStock.setText("Stock: "+item.getStock());
        chosenPrice.setText("Fee: "+item.getRentalFee());
        Image image = new Image(item.getImgSrc());
        chosenPic.setImage(image);
    }

    @FXML
    void enterProfile(MouseEvent event) {
        myAccountLabel.setUnderline(true);
    }

    @FXML
    void exitProfile(MouseEvent event) {
        myAccountLabel.setUnderline(false);
    }

    @FXML
    void enterRentals(MouseEvent event) {
        rentalsText.setUnderline(true);
    }

    @FXML
    void exitRentals(MouseEvent event) {
        rentalsText.setUnderline(false);
    }
    @FXML
    void switchtoProfile(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ProfileView.fxml"));
        root = loader.load();

        ProfileController profileController = loader.getController();

        profileController.setCusIndex(cusIndex);
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchtoRentals(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RentalsView.fxml"));
        root = loader.load();

        RentalsController rentalsController = loader.getController();

        rentalsController.setCusIndex(cusIndex);
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRentingAction(ActionEvent event) {

        if(ManageCustomer.customersList.get(cusIndex).getLevel().matches("Guest") && (ManageCustomer.customersList.get(cusIndex).getRentalNumber() - ManageCustomer.customersList.get(cusIndex).getReturned() == 2)){
            errorText.setText("You can only rent 2 items at a time.");
            return;
        }


        int index = 0;
        for(Item item : ManageItem.items){
            if(chosenId.matches(item.getId())){
                break;
            }
            index++;
        }
        if(ManageCustomer.customersList.get(cusIndex).getLevel().matches("Guest") && (ManageItem.items.get(index).getLoanType().matches("2-day"))){
            errorText.setText("You can not rent this item.");
            return;
        }

        if(ManageItem.items.get(index).getStock() == 0){
            errorText.setText("This item is out of stock.");
            return;
        }


        if(ManageCustomer.customersList.get(cusIndex).getLevel().matches("VIP") ){
            if(ManageCustomer.customersList.get(cusIndex).getRewardPoint() == 100){
                errorText.setText("You can rent this item for free!!");
                ManageCustomer.customersList.get(cusIndex).setRewardPoint(0);
            }else{
                errorText.setText("This item has been added to your rentals list.");
                ManageCustomer.customersList.get(cusIndex).setRewardPoint(ManageCustomer.customersList.get(cusIndex).getRewardPoint()+10);
            }

        }

        Rental newRental = new Rental(ManageItem.items.get(index),1);
        ManageItem.items.get(index).setStock(ManageItem.items.get(index).getStock() - 1);
        ManageCustomer.customersList.get(cusIndex).setRentalNumber(ManageCustomer.customersList.get(cusIndex).getRentalNumber() + 1);
        ManageCustomer.customersList.get(cusIndex).getRentals().add(newRental);
        chosenStock.setText("Stock: "+ManageItem.items.get(index).getStock());


        ManageCustomer.saveFile();
        ManageItem.saveFile();
    }

    @FXML
    void searchAction(MouseEvent event) throws IOException {
        search(searchbar.getText());
    }
    @FXML
    void searchActionenter(ActionEvent event) throws IOException {
        search(searchbar.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.items = ManageItem.items;

        if(items.size() > 0 ){
            setChosenItem(items.get(0));
            listener = new Listener() {
                @Override
                public void onChoose(Item item) {
                    setChosenItem(item);
                }
            };
        }

        int column = 0,row = 0;
        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i),listener);

                if(column == 3){
                    column = 0;
                    row ++;
                }
                grid.add(anchorPane,column++,row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }


    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    public void search(String input) throws IOException {

        for(int i = 0; i< ManageItem.items.size();i++){
            if(ManageItem.items.get(i).getId().toLowerCase().matches(input.toLowerCase()+".*") || ManageItem.items.get(i).getTitle().toLowerCase().matches(".*"+input.toLowerCase()+".*")){
                grid.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(ManageItem.items.get(i),listener);

                grid.add(anchorPane,0,0);


                GridPane.setMargin(anchorPane,new Insets(10));
                return;
            }
        }


    }
}
