package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Classes.ManageItem;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalsController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManageItem.items.remove(ManageItem.items.get(5));
    }
}
