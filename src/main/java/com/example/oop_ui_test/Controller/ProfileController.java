package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private Customer customer;
    private int cusIndex;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Text addressText;
    @FXML
    private Text errorText;

    @FXML
    private Text nameText;

    @FXML
    private Text phoneText;

    @FXML
    private Text typeText;

    @FXML
    private TextField addInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private AnchorPane updatePane;
    @FXML
    private Text itemsLabel;
    @FXML
    private Text rentalsLabel;

    @FXML
    void enterItems(MouseEvent event) {

    }

    @FXML
    void exitItems(MouseEvent event) {

    }

    @FXML
    void enterRentals(MouseEvent event) {

    }



    @FXML
    void exitRentals(MouseEvent event) {
    }


    public void setCusIndex(int cusIndex) {
        this.cusIndex = cusIndex;
        this.customer = ManageCustomer.customersList.get(cusIndex);

        nameText.setText("Name: "+customer.getName());
        addressText.setText("Address: "+customer.getAddress());
        phoneText.setText("Phone Number: "+customer.getPhone());
        typeText.setText("Type of customer: "+customer.getLevel());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void updateButtonAction(ActionEvent event) {
        updatePane.setDisable(false);
        updatePane.setVisible(true);
    }

    @FXML
    void switchtoItems(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("StoreView.fxml"));
        root = loader.load();
        StoreController storeController = loader.getController();

        storeController.setCusIndex(cusIndex);

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void switchtoRentals(MouseEvent event) {

    }


    @FXML
    void confirmButtonAction(ActionEvent event) {


        if (nameInput.getText() == null || nameInput.getText().length() < 1) {
            errorText.setText("Please enter your name");
            return;
        }

        if (addInput.getText() == null || addInput.getText().length() < 1) {
            errorText.setText("Please enter your address");
            return;
        }


        if (!checkNumber(phoneInput.getText()) || phoneInput.getText() == null || phoneInput.getText().length() != 10) {
            errorText.setText("Invalid Phone number! Please enter 10 digit numbers: ");
            return;
        }

        customer.setName(nameInput.getText());
        customer.setAddress(addInput.getText());
        customer.setPhone(phoneInput.getText());

        updatePane.setDisable(true);
        updatePane.setVisible(false);

        ManageCustomer.customersList.set(cusIndex,customer);
        ManageCustomer.saveFile();

        this.setCusIndex(cusIndex);
        nameInput.setText("");
        addInput.setText("");
        phoneInput.setText("");
    }

    private boolean checkNumber(String str){
        try{
            int input = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }
    }
}