import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;


public class TimerController implements Initializable {

    @FXML
    private TextField
            hoursTextField,
            minutesTextField,
            secondsTextField;

    private TextField[] 
            timeFields;

    @FXML
    private Button
            resetButton,
            startStopButton;

    @FXML
    private Node
            root;

    private ProgramTimer
            programTimer;

    private MediaPlayer
            mp;

    private Media
            alarmMedia = new Media(this.getClass().getResource("/default.mp3").toString());


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert hoursTextField == null: "";
        assert minutesTextField == null: "";
        assert secondsTextField == null: "";
        assert resetButton == null: "";
        assert startStopButton == null: "";
        assert root == null: "";

        timeFields = new TextField[]{
                hoursTextField, minutesTextField, secondsTextField};

        programTimer = new ProgramTimer(root, 0);

        root.addEventHandler(Events.TIME_CHANGE, event -> updateTimer());

        root.addEventHandler(Events.FINISHED_COUNTDOWN, event -> {
            resetButton.setDisable(false);

            mp = new MediaPlayer(alarmMedia);
            mp.play();

            this.setStartButton();
        });

        setStartButton();
    }

    private void runTimer(){
        if (programTimer.getTime() > 0) {
            programTimer.start();
            resetButton.setDisable(true);
            setStopButton();
        }
    }

    private void stopTimer(){
        programTimer.stop();
        resetButton.setDisable(false);
        setStartButton();
    }

    @FXML
    public void resetTimer(){
        programTimer.setTime(0);
        updateTimer();
    }

    @FXML
    public void changeHours(ScrollEvent event){
        int direction = (int) Math.copySign(1, event.getDeltaY());

        programTimer.add(direction*3600);
        updateTimer();
    }

    @FXML
    public void changeMinutes(ScrollEvent event){
        int direction = (int) Math.copySign(1, event.getDeltaY());

        programTimer.add(direction*60);
        updateTimer();
    }

    @FXML
    public void changeSeconds(ScrollEvent event){
        if (!programTimer.isRunning) {
            int direction = (int) Math.copySign(1, event.getDeltaY());

            programTimer.add(direction);
            updateTimer();
        }
    }

    private void updateTimer(){
        String[] timeArray = programTimer.getTimeFields();
        for (int i = 0; i < 3; i++)
            timeFields[i].setText(timeArray[i]);
    }

    private void setStartButton() {
        startStopButton.setOnAction(actionEvent -> runTimer());
        startStopButton.setStyle("-fx-background-image: url(\"play.png\");");
    }

    private void setStopButton(){
        startStopButton.setOnAction(actionEvent -> stopTimer());
        startStopButton.setStyle("-fx-background-image: url(\"stop.png\");");
    }
}
