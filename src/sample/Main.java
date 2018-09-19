package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Register.fxml"));
        primaryStage.setTitle("Adaptive Exam  Assignment 1");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


}
