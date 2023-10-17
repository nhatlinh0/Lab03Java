package com.example.lab03_gk;

import com.example.lab03_gk.asm03.models.DigitalBank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static MenuController menuController;
    public static final String CUSTOMER_ID = "001215000001";
    public static final String CUSTOMER_NAME = "Linh";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu.fxml"));
        Parent parent = fxmlLoader.load();
        menuController = (MenuController)fxmlLoader.getController();
        menuController.title.setText(MenuController.TITLE);
        menuController.author.setText(MenuController.AUTHOR);
        menuController.version.setText(MenuController.VERSION);
        menuController.activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        Scene scene = new Scene(parent, 600, 400);
        stage.setTitle("Ngân hàng số");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}