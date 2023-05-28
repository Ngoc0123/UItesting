package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.ManageCustomer;
import com.example.oop_ui_test.Classes.ManageItem;
import com.example.oop_ui_test.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCusController extends Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    int chosenCusIndex = 1;

    @FXML
    private Text customerText;
    @FXML
    private Text logOutText;



    @FXML
    private Text itemText;
    @FXML
    private RadioButton sortByID;

    @FXML
    private RadioButton sortByName;
    @FXML
    private Text errorText;
    @FXML
    private TextField searchBar;
    @FXML
    private Text chosenAddress;

    @FXML
    private Text chosenID;


    @FXML
    private Text chosenLevel;

    @FXML
    private ListView<String> chosenListRental;

    @FXML
    private Text chosenName;

    @FXML
    private Text chosenPassword;

    @FXML
    private Text chosenPhone;

    @FXML
    private Text chosenReward;

    @FXML
    private Text chosenUsername;
    @FXML
    private Text updateErrorText;
    @FXML
    private Text popUpText = new Text();
    @FXML
    private TextField addTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passTextField;

    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button confirmButton;

    @FXML
    private Button createButton;


    @FXML
    private ChoiceBox<String> levelChoice;
    @FXML
    private ChoiceBox<String> levelChoiceUpdate;

    @FXML
    private ListView<String> listView;
    @FXML
    private AnchorPane updatePane;

    @FXML
    void logOutEnter(MouseEvent event) {
        logOutText.setUnderline(true);
    }

    @FXML
    void logOutExit(MouseEvent event) {
        logOutText.setUnderline(false);
    }
    @FXML
    void onLogOutAction(MouseEvent event) throws IOException {
        ManageItem.readFile();
        ManageCustomer.readFile();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        root = loader.load();

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void itemEnter(MouseEvent event) {
        itemText.setUnderline(true);
    }

    @FXML
    void itemExit(MouseEvent event) {
        itemText.setUnderline(false);
    }

    @FXML
    void onRefresh(ActionEvent event) {
        levelChoice.setValue("");
        ManageCustomer.sort(ManageCustomer.SORT_BY_ID);
        refreshList();
        errorText.setText("");
        searchBar.setText("");
        sortByName.setSelected(false);
        sortByID.setSelected(false);


    }


    @FXML
    void cancelUpdateButton(ActionEvent event) {
        updatePane.setVisible(false);
        updatePane.setDisable(true);
    }

    @FXML
    void confirmUpdateButton(ActionEvent event) {
        Customer cus = ManageCustomer.customersList.get(chosenCusIndex);

        if (nameTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter your name");
            return;
        }

        if (addTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter your address");
            return;
        }

        if (!ManageCustomer.checkNumber(phoneTextField.getText()) ||phoneTextField.getText().length() != 10) {
            updateErrorText.setText("Invalid Phone number! Please enter 10 digit numbers: ");
            return;
        }

        if (usernameTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter Username");
            return;
        } else if (ManageCustomer.isExist(usernameTextField.getText()) && !usernameTextField.getText().matches(cus.getUsername())) {
            updateErrorText.setText("This Username is already exist, please enter a new one");
            return;
        }

        if (passTextField.getText().length() < 8 && !passTextField.getText().matches(cus.getPassword())) {
            updateErrorText.setText("Wrong format!! Password must have 8 or more characters. Please enter Password");
            return;
        }

        cus.setName(nameTextField.getText());
        cus.setAddress(addTextField.getText());
        cus.setPhone(phoneTextField.getText());
        cus.setUsername(usernameTextField.getText());
        cus.setPassword(passTextField.getText());
        cus.setLevel(levelChoiceUpdate.getValue());

        ManageCustomer.customersList.set(chosenCusIndex,cus);

        ManageCustomer.saveFile();

        chosenName.setText("Name: "+cus.getName());
        chosenAddress.setText("Address: "+cus.getAddress());
        chosenPhone.setText("Phone number: "+cus.getPhone());
        chosenUsername.setText("Username: " +cus.getUsername());
        chosenPassword.setText("Password: "+cus.getPassword());
        chosenLevel.setText("Type of member: "+cus.getLevel());


        updatePane.setVisible(false);
        updatePane.setDisable(true);
    }
    @FXML
    void onUpdateAction(ActionEvent event) {
        updatePane.setVisible(true);
        updatePane.setDisable(false);
        popUpText.setText("Edit Customer");

        Customer cus = ManageCustomer.customersList.get(chosenCusIndex);

        idTextField.setText(cus.getId());
        nameTextField.setText(cus.getName());
        addTextField.setText(cus.getAddress());
        phoneTextField.setText(cus.getPhone());
        usernameTextField.setText(cus.getUsername());
        passTextField.setText(cus.getPassword());

        ObservableList<String> langs = FXCollections.observableArrayList("Guest", "Regular", "VIP");
        levelChoiceUpdate.setItems(langs);
        levelChoiceUpdate.setValue(cus.getLevel());


        errorText.setText("");
    }
    @FXML
    void onAddAction(ActionEvent event) {
        updatePane.setVisible(true);
        updatePane.setDisable(false);
        popUpText.setText("Add new Customer");
        confirmButton.setVisible(false);
        confirmButton.setDisable(true);
        Customer cus = new Customer();

        idTextField.setText(ManageCustomer.generateID());
        nameTextField.setText("");
        addTextField.setText("");
        phoneTextField.setText("");
        usernameTextField.setText("");
        passTextField.setText("");

        ObservableList<String> langs = FXCollections.observableArrayList("Guest", "Regular", "VIP");
        levelChoiceUpdate.setItems(langs);
        levelChoiceUpdate.setValue(cus.getLevel());


        errorText.setText("");
    }

    @FXML
    void onCreateButton(ActionEvent event) {

        Customer cus = new Customer();

        if (nameTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter your name");
            return;
        }

        if (addTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter your address");
            return;
        }

        if (!ManageCustomer.checkNumber(phoneTextField.getText()) ||phoneTextField.getText().length() != 10) {
            updateErrorText.setText("Invalid Phone number! Please enter 10 digit numbers: ");
            return;
        }

        if (usernameTextField.getText().length() < 1) {
            updateErrorText.setText("Please enter Username");
            return;
        } else if (ManageCustomer.isExist(usernameTextField.getText()) && !usernameTextField.getText().matches(cus.getUsername())) {
            updateErrorText.setText("This Username is already exist, please enter a new one");
            return;
        }

        if (passTextField.getText().length() < 8 && !passTextField.getText().matches(cus.getPassword())) {
            updateErrorText.setText("Wrong format!! Password must have 8 or more characters. Please enter Password");
            return;
        }

        cus.setId(idTextField.getText());
        cus.setName(nameTextField.getText());
        cus.setAddress(addTextField.getText());
        cus.setPhone(phoneTextField.getText());
        cus.setUsername(usernameTextField.getText());
        cus.setPassword(passTextField.getText());
        cus.setLevel(levelChoiceUpdate.getValue());

        ManageCustomer.customersList.add(cus);

        ManageCustomer.saveFile();


        updatePane.setVisible(false);
        updatePane.setDisable(true);

        refreshList();

    }
    @FXML
    void searchBarPress(MouseEvent event) {
        search(searchBar.getText());
    }

    @FXML
    void searchBarEnter(ActionEvent event) {
        search(searchBar.getText());
    }
    @FXML
    private void onItemsPress(MouseEvent event) throws IOException {//on click customer text

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminItemView.fxml"));
        root = loader.load();

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();
        ObservableList<String> langs = FXCollections.observableArrayList("Guest", "Regular", "VIP");
        levelChoice.setItems(langs);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    int cut = 0;
                    if(listView.getSelectionModel().getSelectedIndex() >= 9){
                        cut = 3;
                    }else{
                        cut = 2;
                    }
                    chosenCusIndex = ManageCustomer.find(listView.getSelectionModel().getSelectedItem().substring(cut, cut+4));
                    setChosenCus(chosenCusIndex);
                } catch (NullPointerException e){

                }
            }
        });

        chosenListRental.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

            }
        });

        levelChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listView.getItems().clear();
                ArrayList<String> list = new ArrayList<String>();
                for(int i = 1; i<= ManageCustomer.customersList.size();i++){
                    if(ManageCustomer.customersList.get(i-1).getLevel().matches(levelChoice.getSelectionModel().getSelectedItem())){
                        errorText.setText("");
                        list.add(i+"."+ManageCustomer.customersList.get(i-1).getId() + " - " + ManageCustomer.customersList.get(i-1).getName());

                    }
                }
                searchBar.setText("");
                listView.getItems().addAll(list);
            }
        });


    }

    public void setRadio1() {
        ManageCustomer.sort(ManageCustomer.SORT_BY_NAME);
        refreshList();
        sortByID.setSelected(false);
    }
    public void setRadio2() {
        ManageCustomer.sort(ManageCustomer.SORT_BY_ID);
        refreshList();
        sortByName.setSelected(false);
    }

    public void search(String input){
        listView.getItems().clear();
        int matches = 0;
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1; i<= ManageCustomer.customersList.size();i++){
            if(ManageCustomer.customersList.get(i-1).getName().toLowerCase().matches(".*"+input.toLowerCase()+".*")||ManageCustomer.customersList.get(i-1).getId().toLowerCase().matches(".*"+input)){
                matches++;
                errorText.setText("");
                list.add(i+"."+ManageCustomer.customersList.get(i-1).getId() + " - " + ManageCustomer.customersList.get(i-1).getName());

            }
        }
        listView.getItems().addAll(list);

        if(matches == 0){
            errorText.setText("There is no Customer match with your search!! Please enter another keyword.");
            refreshList();
        }
    }
    public void setChosenCus(int index){
        chosenListRental.getItems().clear();
        chosenID.setText("ID: "+ManageCustomer.customersList.get(index).getId());
        chosenName.setText("Name: "+ManageCustomer.customersList.get(index).getName());
        chosenAddress.setText("Address: "+ManageCustomer.customersList.get(index).getAddress());
        chosenPhone.setText("Phone number: "+ManageCustomer.customersList.get(index).getPhone());
        chosenUsername.setText("Username: "+ManageCustomer.customersList.get(index).getUsername());
        chosenPassword.setText("Password: "+ManageCustomer.customersList.get(index).getPassword());
        chosenLevel.setText("Type of member: "+ManageCustomer.customersList.get(index).getLevel());
        chosenReward.setText("Reward points: "+String.valueOf(ManageCustomer.customersList.get(index).getRewardPoint()));

        ArrayList<String> list = new ArrayList<String>();
        for(int j = 1; j <= ManageCustomer.customersList.get(index).getRentals().size(); j++){
            list.add(j+"."+ManageCustomer.customersList.get(index).getRentals().get(j-1).getId());
        }
        chosenListRental.getItems().addAll(list);
    }

    public void refreshList() throws UnsupportedOperationException {
        listView.getItems().clear();
        ArrayList<String> list = new ArrayList<String>();
        for(int j = 1; j <= ManageCustomer.customersList.size(); j++){
            list.add(j+"."+ManageCustomer.customersList.get(j-1).getId() + " - " + ManageCustomer.customersList.get(j-1).getName() );

        }

        listView.getItems().addAll(list);
        chosenCusIndex = 1;
    }
}
