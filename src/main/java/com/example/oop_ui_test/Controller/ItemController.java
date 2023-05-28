package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Item;
import com.example.oop_ui_test.Listener;
import com.example.oop_ui_test.Main;
import javafx.beans.DefaultProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView itemPic;

    @FXML
    private AnchorPane backgroundPane;

    private Item item;
    private Listener listener;


    public void setData(Item i_item,Listener listener) throws IOException {
        this.item = i_item;
        this.listener=listener;
        nameLabel.setText(this.item.getTitle());
        try {
            Image image = new Image("\\src\\main\\resources\\com\\example\\oop_ui_test\\img\\"+i_item.getRentalType()+".png");
            itemPic.setImage(image);
        }catch (NullPointerException e){
            Image image = new Image("\\src\\main\\resources\\com\\example\\oop_ui_test\\img\\icon.png");
            itemPic.setImage(image);
        }
    }

    @FXML
    void chooseItem(MouseEvent event) {
        listener.onChoose(item);
    }

    @FXML
    void enterItem(MouseEvent event) {
        backgroundPane.setBackground(Background.fill(Paint.valueOf("#6e81d9")));
        nameLabel.setTextFill(Paint.valueOf("#ffffff"));
    }

    @FXML
    void exitItem(MouseEvent event) {
        backgroundPane.setBackground(Background.fill(Paint.valueOf("#f4f4f4")));
        nameLabel.setTextFill(Paint.valueOf("#000000"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
