package ua.javaFX.myprograms.address_book.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.javaFX.myprograms.address_book.objects.Person;
import ua.javaFX.myprograms.address_book.start.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Mocart on 25.01.2016.
 */
public class EditController implements Initializable{
    private Person person;

    private Stage editStage;
    private Stage errorStage;

    private ResourceBundle resourceBundle;

    @FXML
    private Label lblFIO;
    @FXML
    private Label lblPhone;
    @FXML
    private TextField txtFIO;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    public void setEditStage (Stage stage){
        this.editStage = stage;
    }

    public ResourceBundle getRb(){
        return resourceBundle;
    }

    public void actionSave(ActionEvent actionEvent) {
        if (btnSave.getText().equals(resourceBundle.getString("key.delete"))){
            person.setFio("");
            person.setPhone("");

            actionClose(actionEvent);
        }else {
            if (txtFIO.getText().equals("") ||
                    txtPhone.getText().equals("")){
                showErrorDialog();
            } else {
                person.setFio(txtFIO.getText());
                person.setPhone(txtPhone.getText());

                actionClose(actionEvent);
            }
        }
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void setPerson (Person person){
        this.person = person;

        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    public Person getPerson (){
        return person;
    }

    public void changeText(String string){
        switch (string){
            case "btnAdd":
                btnSave.setText(resourceBundle.getString("key.add"));
                break;

            case "btnEdit":
                btnSave.setText(resourceBundle.getString("key.change"));
                break;

            case "btnDelete":
                btnSave.setText(resourceBundle.getString("key.delete"));
                break;
        }
    }

    private void showErrorDialog(){
        if (errorStage == null){
            try {
                FXMLLoader FXMLNullLoader = new FXMLLoader();
                FXMLNullLoader.setLocation(getClass().getResource("../fxml/nullPointError.fxml"));
                FXMLNullLoader.setResources(ResourceBundle.getBundle("ua.javaFX.myprograms.address_book.bundles.Locale",
                        new Locale(Main.LANGUAGE)));
                Parent parentError = FXMLNullLoader.load();

                errorStage = new Stage();
                errorStage.setTitle(FXMLNullLoader.getResources().getString("key.error_title"));
                errorStage.setResizable(false);
                errorStage.setScene(new Scene(parentError));
                errorStage.initModality(Modality.WINDOW_MODAL);
                errorStage.initOwner(editStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        errorStage.show();
    }
}
