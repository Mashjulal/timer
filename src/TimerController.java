import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;


public class TimerController implements Initializable {

    @FXML
    private TextField hoursTextField;
    @FXML
    private TextField minutesTextField;
    @FXML
    private TextField secondsTextField;
    @FXML
    private Button resetButton;
    @FXML
    private Button startStopButton;
    @FXML
    private Node root;

    private ProgramTimer programTimer;
    private MediaPlayer mp;
    private Media alarmMedia =
            new Media(this.getClass().getResource("/alarm.mp3").toString());
    private TextField[] timeFields;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert hoursTextField == null: "Add hoursTextField to your .fxml file!";
        assert minutesTextField == null: "Add minutesTextField to your .fxml file!";
        assert secondsTextField == null: "Add secondsTextField to your .fxml file!";
        assert resetButton == null: "Add resetButton to your .fxml file!";
        assert startStopButton == null: "Add startStopButton to your .fxml file!";
        assert root == null: "Add root to your .fxml file!";

        timeFields = new TextField[]{
                hoursTextField, minutesTextField, secondsTextField};
        programTimer = new ProgramTimer(root, 0);

        // Add event handlers to root
        root.addEventHandler(Events.TIME_CHANGE, event -> updateTimer());
        root.addEventHandler(Events.FINISHED_COUNTDOWN, event -> {
            resetButton.setDisable(false);
            mp = new MediaPlayer(alarmMedia);
            mp.play();
            this.setStartButton();
        });
        setStartButton();
    }

    /**
     * Run {@link ProgramTimer} for specified time period.
     */
    private void runTimer(){
        if (programTimer.getTime() > 0) {
            programTimer.start();
            resetButton.setDisable(true);
            setStopButton();
        }
    }

    /**
     * Stops running timer.
     */
    private void stopTimer(){
        programTimer.stop();
        resetButton.setDisable(false);
        setStartButton();
    }

    /**
     * Resets timer to 0.
     */
    @FXML
    public void resetTimer(){
        programTimer.setTime(0);
        updateTimer();
    }

    /**
     * Change value in hours text field.
     * @param event: ScrollEvent which calls method when user scrolls text field.
     */
    @FXML
    public void changeHours(ScrollEvent event){
        int direction = (int) Math.copySign(1, event.getDeltaY());

        programTimer.add(direction*3600);
        updateTimer();
    }

    /**
     * Change value in minutes text field.
     * @param event: ScrollEvent which calls method when user scrolls text field.
     */
    @FXML
    public void changeMinutes(ScrollEvent event){
        int direction = (int) Math.copySign(1, event.getDeltaY());

        programTimer.add(direction*60);
        updateTimer();
    }

    /**
     * Change value in seconds text field.
     * @param event: ScrollEvent which calls method when user scrolls text field.
     */
    @FXML
    public void changeSeconds(ScrollEvent event){
        if (!programTimer.isRunning()) {
            int direction = (int) Math.copySign(1, event.getDeltaY());

            programTimer.add(direction);
            updateTimer();
        }
    }

    /**
     * Updates hours, minutes and seconds text fields.
     */
    private void updateTimer(){
        String[] timeArray = programTimer.getTimeFields();
        for (int i = 0; i < 3; i++)
            timeFields[i].setText(timeArray[i]);
    }

    /**
     * Sets button state to start and changes click event and image.
     */
    private void setStartButton() {
        startStopButton.setOnAction(actionEvent -> runTimer());
        startStopButton.setStyle("-fx-background-image: url(\"play.png\");");
    }

    /**
     * Sets button state to stop and changes click event and image.
     */
    private void setStopButton(){
        startStopButton.setOnAction(actionEvent -> stopTimer());
        startStopButton.setStyle("-fx-background-image: url(\"stop.png\");");
    }
}
