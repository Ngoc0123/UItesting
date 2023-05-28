package com.example.oop_ui_test.Controller;

import com.example.oop_ui_test.Classes.Customer;
import com.example.oop_ui_test.Classes.ManageCustomer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCusController implements Initializable {

    int chosenCusIndex = 1;

    @FXML
    private Text customerText;

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
    private ChoiceBox<String> levelChoice;
    @FXML
    private ChoiceBox<String> levelChoiceUpdate;

    @FXML
    private ListView<String> listView;
    @FXML
    private AnchorPane updatePane;

    @FXML
    void itemEnter(MouseEvent event) {

    }

    @FXML
    void itemExit(MouseEvent event) {

    }

    @FXML
    void onItemPress(MouseEvent event) {

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
    void searchBarPress(MouseEvent event) {
        search(searchBar.getText());
    }

    @FXML
    void searchBarEnter(ActionEvent event) {
        search(searchBar.getText());
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
                    chosenCusIndex = ManageCustomer.find(listView.getSelectionModel().getSelectedItem().substring(2, 6));
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
