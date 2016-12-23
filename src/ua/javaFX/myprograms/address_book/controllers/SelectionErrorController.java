package ua.javaFX.myprograms.address_book.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 13.02.2016.
 */
public class SelectionErrorController implements Initializable{
    private ResourceBundle resourceBundle;

    @FXML
    Label lblError;
    @FXML
    Button btnOk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        stage.close();
    }
}
