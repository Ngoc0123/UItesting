package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Classes.ManageItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalsController implements Initializable {

    @FXML
    ListView myListView;

    @FXML
    private VBox chosenBox;

    @FXML
    private Text chosenLoanType;

    @FXML
    private Text chosenName;

    @FXML
    private ImageView chosenPic;

    @FXML
    private Text chosenPrice;

    @FXML
    private Text chosenRentalType;

    @FXML
    private Text chosenStock;

    @FXML
    private Text errorText;

    @FXML
    private Label myAccountLabel;

    @FXML
    private Button returnButton;

    @FXML
    void enterProfile(MouseEvent event) {

    }

    @FXML
    void exitProfile(MouseEvent event) {

    }

    @FXML
    void onReturnAction(ActionEvent event) {

    }

    @FXML
    void switchtoProfile(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
