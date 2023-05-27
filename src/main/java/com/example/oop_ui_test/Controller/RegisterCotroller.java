package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;


public class RegisterCotroller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button OkayButton;

    private ArrayList<Customer> customers;

    @FXML
    private Label errorMess;

    @FXML
    private Pane errorPane;

    @FXML
    private TextField addressIn;

    @FXML
    private TextField fullnameIn;


    @FXML
    private PasswordField passwordIn;
    @FXML
    private Pane Succeed;
    @FXML
    private Button uki;

    @FXML
    private Button Login;

    @FXML
    private TextField phoneIn;

    @FXML
    private PasswordField reEnterPassWordIn;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userNameIn;

    String name = null, address = null, phone = null, userName = null, password = null, reEnterPassWord = null;

    @FXML
    void ukiButton(){
        uki.setOnAction(event -> Succeed.setVisible(false));
    }

    @FXML
    void switchtoLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        root = loader.load();
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onSubmitButton(ActionEvent event) {
        String tmp = fullnameIn.getText();
        if (tmp == null || tmp.length() < 1) {
            errorMess.setText("Please enter your name");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        }
        name = tmp;

        tmp = addressIn.getText();
        if (tmp == null || tmp.length() < 1) {
            errorMess.setText("Please enter your address");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        }
        address = tmp;

        tmp = phoneIn.getText();
        if (!checkNumber(tmp) || tmp == null || tmp.length() != 10) {
            errorMess.setText("Invalid Phone number! Please enter 10 digit numbers: ");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        } else {
            phone = phoneIn.getText();
        }

        tmp = userNameIn.getText();
        if (tmp == null || tmp.length() < 1) {
            errorMess.setText("Please enter your User name");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        } else if (ManageCustomer.isExist(tmp)) {
            errorMess.setText("This User Name is already exist, please enter a new one");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;


        }
        userName = tmp;



        tmp = passwordIn.getText();
        if (tmp == null || tmp.length() < 8) {
            errorMess.setText("Wrong format!! Password must have 8 or more characters. Please enter your Password ");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        }
        password = passwordIn.getText();


        tmp = reEnterPassWordIn.getText();
        if (!tmp.matches(password)) {
            errorMess.setText("You have re-entered  wrong password, Please re-enter password again ");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
            return;
        }
        reEnterPassWord = reEnterPassWordIn.getText();

        createCustomer();
        Succeed.setVisible(true);


    }
    private void createCustomer(){

        Customer newCus = new Customer();
        newCus.setId(ManageCustomer.generateID());
        newCus.setName(name);
        newCus.setAddress(address);
        newCus.setPhone(phone);
        newCus.setRentalNumber(0);
        newCus.setLevel("Guest");
        newCus.setUsername(userName);
        newCus.setPassword(password);
        ManageCustomer.customersList.add(newCus);
        ManageCustomer.saveFile();

    }




    @FXML
    private void okayButton(){
        errorPane.setDisable(true);
        errorPane.setVisible(false);
    }


    public boolean checkNumber(String str){
        try{
            int input = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }
    }




}
