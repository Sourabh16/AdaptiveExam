package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.Utility.SwapScreen;
import sample.Utility.dbconnection;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ImageExamController implements Initializable {

    @FXML
    private Label qLabel;

    @FXML
    private Label qType;

    @FXML
    private Label qCount,scoreLbl;

    @FXML
    private AnchorPane content;

    private String answer;

    private int score=0,mathsScore=0,queCount=1;

    String url1,url2;


    @FXML
    private  ImageView Imageview1,Imageview2;

    private String QueryMed="Select Top 1 * from ImageExam where QType='Medium' order by newid()";
    private String QueryEasy="Select Top 1 * from ImageExam where QType='Easy' order by newid()";
    private String QueryHard="Select Top 1 * from ImageExam where QType='Hard' order by newid()";
    private ResultSet rs;

    @FXML
    public void MediumQue()
    {
        if(queCount==5)
        {
            SwapScreen swap=new SwapScreen();
            try {
                swap.changeScene("Views/Home.fxml",content);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryMed);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                 url1=rs.getString("url1");
                 url2=rs.getString("url2");
                Image image1 = new Image(url1);
                // Setting the image view
                Imageview1.setImage(image1);

                Image image2 = new Image(url2);
                // Setting the image view
                Imageview2.setImage(image2);
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Blue"));

                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void EasyQue()
    {
        if(queCount==5)
        {
            SwapScreen swap=new SwapScreen();
            try {
                swap.changeScene("Views/Home.fxml",content);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryEasy);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                 url1=rs.getString("url1");
                 url2=rs.getString("url2");
                Image image1 = new Image(url1);
                // Setting the image view
                Imageview1.setImage(image1);
                Image image2 = new Image(url2);
                // Setting the image view
                Imageview2.setImage(image2);
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Blue"));

                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void HardQue()
    {
        if(queCount==5)
        {
            SwapScreen swap=new SwapScreen();
            try {
                swap.changeScene("Views/Home.fxml",content);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryHard);
            if(rs.next()) {

                qLabel.setText(rs.getString("Question"));
                 url1=rs.getString("url1");
                 url2=rs.getString("url2");
                Image image1 = new Image(url1);
                // Setting the image view
                Imageview1.setImage(image1);
                Image image2 = new Image(url2);
                // Setting the image view
                Imageview2.setImage(image2);
                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Blue"));

                qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void changeQuestion(String check)
    {
        if(qType.getText().equals("Easy")&& queCount<6)
        {
            if(MatchAnswer(check))
            {
                score += 2;
                MediumQue();
            }
            else{EasyQue();}
        }

        else if(qType.getText().equals("Medium")&& queCount<6)
        {
            if(MatchAnswer(check))
            {
                score += 5;
                HardQue();
            }
            else{EasyQue();}
        }
        else if(qType.getText().equals("Hard")&& queCount<6)
        {
            if(MatchAnswer(check))
            {
                score += 10;
                HardQue();
            }
            else{MediumQue();}

        }



    }

    public boolean MatchAnswer(String check)
    {
        dbconnection db=new dbconnection();
        try {
            String query="select * from imageExam where Question='"+qLabel.getText()+"'";
            rs=db.dbExecuteQuery(query);
            if(rs.next())
            {


                if(check.equals(rs.getString("Answer")))
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

    //ImageClick
    @FXML
    private void AnswerClick (MouseEvent mouseEvent)
    {



             if(mouseEvent.getSource()==Imageview1)
             {
                 answer="1";
             }
             else if(mouseEvent.getSource()==Imageview2)
             {
                 answer="2";
             }
             changeQuestion(answer);



    }



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        MediumQue();
    }
}
