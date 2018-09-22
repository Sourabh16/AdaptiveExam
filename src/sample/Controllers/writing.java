package sample.Controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Utility.SwapScreen;



public class writing  {

    @FXML
    private AnchorPane content;

    public void change(ActionEvent event)
    {


        SwapScreen swap=new SwapScreen();
        try {
            swap.changeScene("Views/ScoreCard.fxml",content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
