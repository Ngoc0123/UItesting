package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.Item;
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




    @FXML
    void logInButtonAction(ActionEvent event) throws IOException {

        if(userNameInput.getText().isEmpty()){
            displayError("Please enter your username!");
            return;
        }else if(passInput.getText().isEmpty()){
            displayError("Please enter your password!");
            return;
        }


        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();

        Scanner fileScanner = new Scanner(new FileReader(path.toString()+"\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\customers.txt"));
        customer = new Customer();

        boolean found = false;
        while (fileScanner.hasNextLine()){

            String line = fileScanner.nextLine();
            StringTokenizer inReader = new StringTokenizer(line,",");
            if(inReader.countTokens() != 8) {

            }else {
                customer.setId(inReader.nextToken());
                customer.setName(inReader.nextToken());
                customer.setAddress(inReader.nextToken());
                customer.setPhone(inReader.nextToken());
                customer.setRentalNumber(Integer.parseInt(inReader.nextToken()));
                customer.setLevel(inReader.nextToken());
                customer.setUsername(inReader.nextToken());
                customer.setPassword(inReader.nextToken());

                if(stringCompare(customer.getUsername(),userNameInput.getText())==0){
                    if (stringCompare(customer.getPassword(),passInput.getText())==0){
                        found = true;
                        break;
                    }
                }

            }

        }

        if(found){


            FXMLLoader loader = new FXMLLoader(Main.class.getResource("StoreView.fxml"));
            root = loader.load();

            StoreController storeController = loader.getController();

            storeController.setCurrent(customer);
            storeController.setItems(itemArrayList);


            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }else{
            displayError("Your username and password are not correct! Please enter again!");
        }

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void displayError(String errStr){
        errorPane.setVisible(true);
        errorPane.setDisable(false);
        okayErrorButton.setDisable(false);
        okayErrorButton.setVisible(true);
        errorText.setText(errStr);
    }
}