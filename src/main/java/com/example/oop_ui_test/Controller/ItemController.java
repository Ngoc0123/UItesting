package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemController {

    @FXML
    private Label nameLabel;
    private Item item;

    public void setData(Item item){
        this.item = item;
        nameLabel.setText(this.item.getTitle());
    }



}
