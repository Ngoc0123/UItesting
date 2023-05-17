package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.Item;
import com.example.oop_ui_test.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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

public class StoreController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Customer current;
    ArrayList<Item> items;

    public StoreController() {
        current = new Customer();
        items = new ArrayList<Item>();
    }

    @FXML
    void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("LoginView.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Path path = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(path.toString()+"\\src\\main\\java\\com\\example\\oop_ui_test\\Data\\items.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(fileScanner.hasNextLine()){
            Item item = new Item();

            String line = fileScanner.nextLine();
            StringTokenizer inReader = new StringTokenizer(line,",");
            //if(inReader.countTokens() == 7){
            item.setId(inReader.nextToken());
            item.setTitle(inReader.nextToken());
            item.setRentalType(inReader.nextToken());
            item.setLoanType(inReader.nextToken());
            item.setStock(Integer.parseInt(inReader.nextToken()));
            item.setRentalFee(Double.parseDouble(inReader.nextToken()));
            //item.setGenre(inReader.nextToken());
            //}
            items.add(item);
        }

        int column = 0,row = 0;
        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Item.fxml"));
                //fxmlLoader.setLocation(Main.class.getResource(path.toString()+"\\src\\main\\resource\\com\\example\\oop_ui_test\\Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i));

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

    public void setCurrent(Customer customer) {
        this.current = customer;
    }
    public void setItems(ArrayList<Item> itemList){
        this.items = itemList;
    }

}
