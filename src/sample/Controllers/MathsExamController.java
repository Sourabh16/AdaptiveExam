package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;
import sample.Utility.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MathsExamController implements Initializable {

    @FXML
    private Label qLabel;

    @FXML
    private Label qType;

    @FXML
    private Label qCount,scoreLbl;

    @FXML
    private AnchorPane content;

    private int score=0,mathsScore=0,queCount=0,overallScore=0;

    @FXML
    private TextField answerTbox;
    @FXML
    private Button nxtButton;

    private String QueryMed="Select Top 1 * from Maths where QType='Medium' order by newid()";
    private String QueryEasy="Select Top 1 * from Maths where QType='Easy' order by newid()";
    private String QueryHard="Select Top 1 * from Maths where QType='Hard' order by newid()";
    private ResultSet rs;

    public void store()
    {
        Main.overallScoreGlobal=Integer.parseInt(scoreLbl.getText());
        Main.mathsScoreGlobal=Integer.parseInt(scoreLbl.getText());
    }

    @FXML
    public void MediumQue()
    {
        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryMed);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Blue"));
                answerTbox.clear();
                queCount++;
                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                if(queCount==4)
                {
                    nxtButton.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean MatchAnswer()
    {
        dbconnection db=new dbconnection();
        try {
            String query="select * from Maths where Question='"+qLabel.getText()+"'";
            rs=db.dbExecuteQuery(query);
            if(rs.next())
            {

                if(answerTbox.getText().equals(rs.getString("Answer")))
                {
                    return true;
                }
                else {return false;}


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void EasyQue()
    {
        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryEasy);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Green"));
                answerTbox.clear();
                queCount++;
                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                if(queCount==4)
                {
                    nxtButton.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void HardQue()
    {
        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryHard);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Red"));
                answerTbox.clear();
                queCount++;
                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                if(queCount==4)
                {
                    nxtButton.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void changeQuestion (ActionEvent actionEvent)
    {
        if(qType.getText().equals("Easy") && nxtButton.getText().equals("Next"))
        {
            if(MatchAnswer())
            {
                score += 2;

                MediumQue();
            }
            else{EasyQue();}
        }

        else if(qType.getText().equals("Medium")&& nxtButton.getText().equals("Next"))
        {
            if(MatchAnswer())
            {
                score += 5;

                HardQue();
            }
            else{EasyQue();}
        }
        else if(qType.getText().equals("Hard")&& nxtButton.getText().equals("Next"))
        {
            if(MatchAnswer())
            {
                score += 10;

                HardQue();
            }
            else{MediumQue();}

        }
        else if(nxtButton.getText().equals("Submit"))
        {
            if(qType.getText().equals("Easy")) {
                if (MatchAnswer()) {
                    score += 2;
                }
            }
            else if(qType.getText().equals("Medium")) {
                if (MatchAnswer()) {
                    score += 5;
                }
            }
            else if(qType.getText().equals("Hard")) {
                if (MatchAnswer())
                {   score += 10;}
            }
            store();
            //SwapScreen swap=new SwapScreen();
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Views/ImageExam.fxml"));
                Parent pa = (AnchorPane) loader.load();
                ImageExamController out=new ImageExamController();
                out=loader.getController();
                out.vault(Main.overallScoreGlobal,Main.mathsScoreGlobal);
                Scene newScene = new Scene(pa);
                Stage newStage = (Stage) content.getScene().getWindow();
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        MediumQue();

    }
}
