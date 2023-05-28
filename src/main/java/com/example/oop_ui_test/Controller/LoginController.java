package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.*;
import com.example.oop_ui_test.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<Customer> customers;
    private Customer customer;

    private ArrayList<Item> itemArrayList;
    @FXML
    private TextField passInput;

    @FXML
    private TextField userNameInput;

    @FXML
    private Rectangle errorPane;

    @FXML
    private Text errorText;

    @FXML
    private Button okayErrorButton;

    public LoginController(ArrayList<Customer> customers, ArrayList<Item> itemArrayList) {
        this.customers = customers;
        this.itemArrayList = itemArrayList;
    }
    public LoginController(){
    }

    @FXML
    void logInButtonAction(ActionEvent event) throws IOException {

        if(userNameInput.getText().isEmpty()){
            displayError("Please enter your username!");
            return;
        }else if(passInput.getText().isEmpty()){
            displayError("Please enter your password!");
            return;
        }

        if(userNameInput.getText().matches(Admin.username)){
            if(passInput.getText().matches(Admin.password)){
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminItemView.fxml"));
                root = loader.load();

                stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
                scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        }

        boolean found = false;
        int i;

        for(i=0; i < customers.size();i++){
            if(customers.get(i).getUsername().matches(userNameInput.getText())){
                if (customers.get(i).getPassword().matches(passInput.getText())){
                    found = true;
                    customer = customers.get(i);
                    break;
                }
            }
        }
        if(found){


            FXMLLoader loader = new FXMLLoader(Main.class.getResource("StoreView.fxml"));
            root = loader.load();

            StoreController storeController = loader.getController();

            storeController.setCusIndex(i);
            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }else{
            displayError("Your username or password are not correct! Please enter again!");
        }



    }

    @FXML
    void signUpButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegisterView.fxml"));
        root = loader.load();
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void errorButtonAction(ActionEvent event) {
        errorText.setText(null);
        errorPane.setVisible(false);
        errorPane.setDisable(true);
        okayErrorButton.setVisible(false);
        okayErrorButton.setDisable(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws RuntimeException{
        customers = ManageCustomer.customersList;
    }


    public void displayError(String errStr){
        errorPane.setVisible(true);
        errorPane.setDisable(false);
        okayErrorButton.setDisable(false);
        okayErrorButton.setVisible(true);
        errorText.setText(errStr);
    }
}