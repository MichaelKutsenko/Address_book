 package ua.javaFX.myprograms.address_book.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.javaFX.myprograms.address_book.interfaces.impl.CollectionAddressBook;
import ua.javaFX.myprograms.address_book.objects.Person;
import ua.javaFX.myprograms.address_book.start.Main;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    private CollectionAddressBook cab = new CollectionAddressBook();

    private Stage mainStage;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditController editDialogController;
    private Stage editDialogStage;

    private Stage selectionErrorStage;

    private ResourceBundle rb;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableAddressBook;
    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private Label lblCount;

    public void setMainStage (Stage stage){
        this.mainStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rb = resources;

        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        initListeners();

        cab.fillTestData();

        tableAddressBook.setItems(cab.getPersons());

        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("ua.javaFX.myprograms.address_book.bundles.Locale",
                    new Locale(Main.LANGUAGE)));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        cab.getPersons().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateLabel();
            }
        });

        tableAddressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2){
                    editDialogController.changeText(btnEdit.getId());
                    editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog(btnEdit.getId());
                }
            }
        });
    }

    private void updateLabel (){
        lblCount.setText(rb.getString("key.count") + ": " + cab.getPersons().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) actionEvent.getSource();
        
        editDialogController.changeText(clickedButton.getId());

        switch (clickedButton.getId()){
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog(btnAdd.getId());

                if (!(editDialogController.getPerson().getFio().equals("") &&
                        editDialogController.getPerson().getPhone().equals(""))){
                    cab.addPerson(editDialogController.getPerson());
                }

                break;

            case "btnEdit":
                try {
                    editDialogController.setPerson(selectedPerson);
                    showDialog(btnEdit.getId());
                } catch (NullPointerException e){
                    showErrorDialog();
                }

                break;

            case "btnDelete":
                try {
                    editDialogController.setPerson(selectedPerson);
                    showDialog(btnDelete.getId());
                    if (editDialogController.getPerson().getFio().equals("") &&
                            editDialogController.getPerson().getPhone().equals("")){
                        cab.deletePerson(editDialogController.getPerson());
                    }

                } catch (NullPointerException e){
                    showErrorDialog();
                }

                break;
        }
    }

    private void showDialog(String buttonID){
         if (editDialogStage == null){
             editDialogStage = new Stage();
             editDialogStage.setMinHeight(150);
             editDialogStage.setMinWidth(300);
             editDialogStage.setResizable(false);
             editDialogStage.setScene(new Scene(fxmlEdit));
             editDialogStage.initModality(Modality.WINDOW_MODAL);
             editDialogStage.initOwner(mainStage);
             editDialogController.setEditStage(editDialogStage);
         }

        if (buttonID.equals(btnAdd.getId())){
            editDialogStage.setTitle(rb.getString("key.add_contact"));
        } else if (buttonID.equals(btnEdit.getId())) {
            editDialogStage.setTitle(rb.getString("key.edit_contact"));
        } else editDialogStage.setTitle(rb.getString("key.delete_contact"));

        editDialogStage.showAndWait();
    }

    private void showErrorDialog() {
        if (selectionErrorStage == null){
            try {
                FXMLLoader selErrorLoader = new FXMLLoader();
                selErrorLoader.setLocation(getClass().getResource("../fxml/selectionError.fxml"));
                selErrorLoader.setResources(ResourceBundle.getBundle("ua.javaFX.myprograms.address_book.bundles.Locale",
                        new Locale(Main.LANGUAGE)));
                Parent fxmlSelError = selErrorLoader.load();

                selectionErrorStage = new Stage();
                selectionErrorStage.setTitle(rb.getString("key.error_title"));
                selectionErrorStage.setResizable(false);
                selectionErrorStage.initModality(Modality.WINDOW_MODAL);
                selectionErrorStage.initOwner(mainStage);
                selectionErrorStage.setScene(new Scene(fxmlSelError));

                selectionErrorStage.setTitle(rb.getString("key.error_title"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        selectionErrorStage.show();
    }
}
