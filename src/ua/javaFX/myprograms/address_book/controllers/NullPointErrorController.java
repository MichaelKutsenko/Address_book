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
 * Created by Lenovo on 08.02.2016.
 */
public class NullPointErrorController implements Initializable {
    private ResourceBundle rb;

    @FXML
    Label lblError;
    @FXML
    Button btnOk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rb = resources;
    }

    public void actionRetry(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
