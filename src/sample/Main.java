package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Utility.dbconnection;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    static Stage stage=null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/Register.fxml"));
        primaryStage.setTitle("Adaptive Exam  Assignment 1");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {


        launch(args);
    }


}
