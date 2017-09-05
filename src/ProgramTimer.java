import javafx.application.Platform;
import javafx.scene.Node;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which complete work with time.
 */
class ProgramTimer {
    private Timer timer;
    private int time = 0;
    private Node node;
    private boolean isRunning = false;

    /**
     * Constructor with parameters.
     * @param node: Application node
     * @param t: initial time
     */
    ProgramTimer(Node node, int t){
        this.node = node;
        time = t;
    }

    /**
     * Converts time two string array.
     * @return string array with time values: hours, minutes seconds.
     */
    String[] getTimeFields(){
        String[] timeArray = new String[3];
        int timeCopy = time;
        for (int i = 0; i < 3; i++){
            int t = (int) (timeCopy / Math.pow(60, 2-i));
            timeArray[i] = String.format("%02d", t);
            timeCopy -= t * Math.pow(60, 2-i);
        }
        return timeArray;
    }

    void setTime(int t){
        time = t;
    }

    /**
     * Runs timer.
     */
    void start(){
        isRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setInterval();
                // If time runs out then fire countdown event
                if (time == 0)
                    Platform.runLater(() -> node.fireEvent(new Events.FinishedCountdownEvent()));
                node.fireEvent(new Events.TimeChangeEvent());
            }
        }, 1000, 1000);
    }

    /**
     * Change time.
     * @return time value.
     */
    private int setInterval() {
        if (time == 1)
            stop();
        return --time;
    }

    /**
     * Stops running timer.
     */
    void stop(){
        if (!isRunning)
            return;

        timer.cancel();
        timer.purge();
        isRunning = false;
    }

    /**
     * Increases time.
     * @param n: value which would be added to time.
     */
    void add(int n){
        time += n;
        if (time < 0) time = 0;
        else if (time > 86400) time = 86400;
    }

    int getTime(){
        return time;
    }

    boolean isRunning() {
        return isRunning;
    }
}
