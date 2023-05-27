package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.*;
import com.example.oop_ui_test.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RentalsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int cusIndex;
    private Customer currentCus = new Customer();
    private Item chosenItem = new Item();
    private int chosenItemIndex = 0;
    private int chosenRentalIndex;
    @FXML
    ListView<String> myListView;


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
    private Text chosenStatus;
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
        if(currentCus.getRentals().get(chosenRentalIndex).getStatus().matches("Returned")){
            errorText.setText("This rental was returned.");
            return;
        }

        currentCus.getRentals().get(chosenRentalIndex).setStatus("Returned");
        ManageCustomer.customersList.get(cusIndex).setRentals(currentCus.getRentals());
        chosenStatus.setText("Returned");
        ManageItem.items.get(chosenItemIndex).setStock(ManageItem.items.get(chosenItemIndex).getStock()+1);
        ManageCustomer.customersList.get(cusIndex).setReturned(ManageCustomer.customersList.get(cusIndex).getReturned()+1);
        ManageCustomer.updateLevel(cusIndex);
        errorText.setText("Success!!!");

        ManageItem.saveFile();
        ManageCustomer.saveFile();

    }
    @FXML
    void switchtoProfile(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ProfileView.fxml"));
        root = loader.load();

        ProfileController profileController = loader.getController();

        profileController.setCusIndex(cusIndex);
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                chosenRentalIndex = myListView.getSelectionModel().getSelectedIndex();
                System.out.println(chosenRentalIndex);
                setChosenRental(chosenRentalIndex);
                errorText.setText("");
            }
        });
    }

    private void setChosenRental(int index){
        chosenItemIndex = 0;
        for(Item item: ManageItem.items){
            if(item.getId().matches(currentCus.getRentals().get(index).getId())){
                chosenItem = item;
                break;
            }
            chosenItemIndex++;
        }

        chosenName.setText(chosenItem.getTitle());
        chosenLoanType.setText("Loan type :"+chosenItem.getLoanType());

        chosenStatus.setText("Status: "+currentCus.getRentals().get(chosenRentalIndex).getStatus());

        chosenPrice.setText("Price: $"+String.valueOf(chosenItem.getRentalFee()));
        chosenPic.setImage(new Image(chosenItem.getImgSrc()));
    }

    public void setCusIndex(int i ){
        this.cusIndex = i;
        this.currentCus = ManageCustomer.customersList.get(cusIndex);

        ArrayList<String> list = new ArrayList<String>();
        for(int j = 1; j <= currentCus.getRentals().size();j++){
            list.add(j+"."+currentCus.getRentals().get(j-1).getId());
        }

        myListView.getItems().addAll(list);
        chosenRentalIndex = 0;
        setChosenRental(0);

    }


}
