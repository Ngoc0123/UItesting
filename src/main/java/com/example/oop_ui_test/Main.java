package com.example.oop_ui_test;

import com.example.oop_ui_test.Classes.Item;
import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Classes.ManageItem;
import com.example.oop_ui_test.Classes.Rental;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Image image = new Image(path.toString()+ "\\src\\main\\resources\\com\\example\\oop_ui_test\\img\\StoreIcon.png");
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.getIcons().add(image);
        stage.setTitle("Genie's Store");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {

        ManageCustomer.readFile();
        ManageItem.readFile();

        launch();
    }


}