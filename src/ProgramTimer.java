import javafx.application.Platform;
import javafx.scene.Node;

import java.util.Timer;
import java.util.TimerTask;

public class ProgramTimer {
    Timer timer;
    int time = 0;
    Node node;
    Boolean isRunning = false;

    public ProgramTimer(Node node, int t){
        this.node = node;
        time = t;
    }

    public String[] getTimeFields(){
        String[] timeArray = new String[3];
        int timeCopy = time;

        for (int i = 0; i < 3; i++){
            int t = (int) (timeCopy / Math.pow(60, 3-i-1));
            timeArray[i] = ((t > 9) ? "": "0") + t;
            timeCopy -= t * Math.pow(60, 3-i-1);
        }
        return timeArray;
    }

    public void setTime(int t){
        time = t;
    }

    public void start(){
        isRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setInterval();
                if (time == 0)
                Platform.runLater(() -> node.fireEvent(new Events.FinishedCountdownEvent()));
                node.fireEvent(new Events.TimeChangeEvent());}
        }, 1000, 1000);
    }

    private int setInterval() {
        if (time == 1){
            timer.cancel();
            timer.purge();
            isRunning = false;
        }
        return --time;
    }

    public void stop(){
        timer.cancel();
        timer.purge();
        isRunning = false;
    }

    public void add(int n){
        time += n;
        if (time < 0) time = 0;
        else if (time > 86400) time = 86400;
    }

    public int getTime(){
        return time;
    }
}
