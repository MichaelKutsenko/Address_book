package ua.javaFX.myprograms.address_book.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.javaFX.myprograms.address_book.controllers.MainController;
import ua.javaFX.myprograms.address_book.interfaces.impl.CollectionAddressBook;
import ua.javaFX.myprograms.address_book.objects.Person;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static String LANGUAGE = "ru";

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        mainLoader.setResources(ResourceBundle.getBundle("ua.javaFX.myprograms.address_book.bundles.Locale",
                new Locale(LANGUAGE)));
        Parent root = mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(mainLoader.getResources().getString("key.address_book"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(346);
        primaryStage.setMinHeight(360);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
