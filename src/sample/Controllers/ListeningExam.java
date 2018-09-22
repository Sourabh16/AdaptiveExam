package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;
import sample.Utility.SwapScreen;
import sample.Utility.dbconnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListeningExam implements Initializable {


private MediaPlayer audioPlayer;

    @FXML
    private Label qLabel;

    @FXML
    private Label qType;

    @FXML
    private Label qCount,scoreLbl,overAllscoreLbl,MatLb,imgLbl,matLbl;

    @FXML
    private AnchorPane content;

    @FXML
    private RadioButton rd1,rd2,rd3,rd4;

   // private String answer;

    private int score=0,mathsScore=0,queCount=0,imageScore,overallScore;
    private ResultSet rs;
    private String radioAnswer;

    @FXML
    private Button nxtBut;

    private String answer="0";

    private String QuestionLbl;


    @FXML
    private ImageView ImageView1;

    private String QueryMed="Select Top 1 * from Listening where QType='Medium' order by newid()";
    private String QueryEasy="Select Top 1 * from Listening where QType='Easy' order by newid()";
    private String QueryHard="Select Top 1 * from Listening where QType='Hard' order by newid()";

    public void MediumQue() {

        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryMed);
            if(rs.next()) {

                QuestionLbl=rs.getString("Question");
                File audioFile = new File(rs.getString("Question"));
                Media audio = new Media(audioFile.toURI().toString());
                audioPlayer = new MediaPlayer(audio);

                rd1.setText(rs.getString("option1"));
                rd2.setText(rs.getString("option2"));
                rd3.setText(rs.getString("option3"));
                rd4.setText(rs.getString("option4"));
                ToggleGroup radioGroup = new ToggleGroup();
                rd1.setToggleGroup(radioGroup);
                rd2.setToggleGroup(radioGroup);
                rd3.setToggleGroup(radioGroup);
                rd4.setToggleGroup(radioGroup);

                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Blue"));

                //qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;
                if(queCount==3)
                {
                    nxtBut.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void EasyQue() {


        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryEasy);
            if(rs.next()) {
                QuestionLbl=rs.getString("Question");
                File audioFile = new File(rs.getString("Question"));
                Media audio = new Media(audioFile.toURI().toString());
                audioPlayer = new MediaPlayer(audio);

                rd1.setText(rs.getString("option1"));
                rd2.setText(rs.getString("option2"));
                rd3.setText(rs.getString("option3"));
                rd4.setText(rs.getString("option4"));
                ToggleGroup radioGroup = new ToggleGroup();
                rd1.setToggleGroup(radioGroup);
                rd2.setToggleGroup(radioGroup);
                rd3.setToggleGroup(radioGroup);
                rd4.setToggleGroup(radioGroup);


                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("Green"));

                //qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;
                if(queCount==3)
                {
                    nxtBut.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void HardQue() {

        dbconnection db=new dbconnection();
        try {

            rs=db.dbExecuteQuery(QueryHard);
            if(rs.next()) {
                QuestionLbl=rs.getString("Question");
                File audioFile = new File(rs.getString("Question"));
                Media audio = new Media(audioFile.toURI().toString());
                audioPlayer = new MediaPlayer(audio);

                rd1.setText(rs.getString("option1"));
                rd2.setText(rs.getString("option2"));
                rd3.setText(rs.getString("option3"));
                rd4.setText(rs.getString("option4"));
                ToggleGroup radioGroup = new ToggleGroup();
                rd1.setToggleGroup(radioGroup);
                rd2.setToggleGroup(radioGroup);
                rd3.setToggleGroup(radioGroup);
                rd4.setToggleGroup(radioGroup);

                qType.setText(rs.getString("QType"));
                qType.setTextFill(Color.web("RED"));


                //qCount.setText("Question."+String.valueOf(queCount)+":");
                scoreLbl.setText(String.valueOf(score));
                queCount++;
                if(queCount==3)
                {
                    nxtBut.setText("Submit");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void playAudio(MouseEvent event)
    {
        if(event.getSource()==ImageView1)
        {
            audioPlayer.play();
        }
    }

    @FXML
    private void nextQuestion(MouseEvent actionEvent)
    {
        if(actionEvent.getSource()==nxtBut) {

            if (qType.getText().equals("Easy") && nxtBut.getText().equals("Next")) {


                if (MatchAnswer(answer)) {
                    score += 2;
                    MediumQue();
                } else {
                    EasyQue();
                }
            } else if (qType.getText().equals("Medium") && nxtBut.getText().equals("Next")) {
                if (MatchAnswer(answer)) {
                    score += 5;
                    HardQue();
                } else {
                    EasyQue();
                }
            } else if (qType.getText().equals("Hard") && nxtBut.getText().equals("Next")) {
                if (MatchAnswer(answer)) {
                    score += 10;
                    HardQue();
                } else {
                    MediumQue();
                }

            } else if (nxtBut.getText().equals("Submit")) {
                if(qType.getText().equals("Easy")) {
                    if (MatchAnswer(answer)) {
                        score += 2;
                    }
                }
                else if(qType.getText().equals("Medium")) {
                    if (MatchAnswer(answer)) {
                        score += 5;
                    }
                }
                else if(qType.getText().equals("Hard")) {
                    if (MatchAnswer(answer))
                    {   score += 10;}
                }
                store();
                SwapScreen swap = new SwapScreen();
                try {
                    Main.listeningScoreGlobal=score;
                    Main.overallScoreGlobal=Main.overallScoreGlobal+score;
                   // FXMLLoader loader = new FXMLLoader(Main.class.getResource("Views/SpellingExam.fxml"));
                  swap.changeScene("Views/SpellingExam.fxml",content);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public boolean MatchAnswer(String check)
    {
        dbconnection db=new dbconnection();
        try {
            String query="select * from Listening where Question='"+QuestionLbl+"'";
            rs=db.dbExecuteQuery(query);
            if(rs.next())
            {

                if(check.equals(rs.getString("Answer")))
                {
                    return true;

                }
                else {
                    return false;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MediumQue();
    }

    public void getAnswer(ActionEvent event)
    {
        if(event.getSource()==rd1 || event.getSource()==rd2 || event.getSource()==rd3 || event.getSource()==rd4)
        {
            if (rd1.isSelected()) {
                answer = rd1.getText();
            }
            if (rd2.isSelected()) {
                answer = rd2.getText();
            }
            if (rd3.isSelected()) {
                answer = rd3.getText();
            }
            if (rd4.isSelected()) {
                answer = rd4.getText();
            }

        }

    }

    public void vault(int overallScore,int mathsScore,int ImgScore)
    {

        overAllscoreLbl.setText(String.valueOf(overallScore));
        matLbl.setText(String.valueOf(mathsScore));
        imgLbl.setText(String.valueOf(ImgScore));

        System.out.println(overallScore);
    }

    public void store()
    {
        //int value=Integer.parseInt(overAllscoreLbl.getText());
        Main.mathsScoreGlobal=Integer.parseInt(matLbl.getText());
        Main.imageScoreGlobal=Integer.parseInt(imgLbl.getText());
        Main.listeningScoreGlobal=score;
        Main.overallScoreGlobal=Main.overallScoreGlobal+score;

    }


}
