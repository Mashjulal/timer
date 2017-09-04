import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by master on 06.03.17.
 */
public class Main extends Application {
    private Stage primaryStage;
    private Parent root;

    @Override
    public void start(Stage stage){
        primaryStage = stage;
        startApplication();
    }

    private void startApplication(){
        try {
            root = FXMLLoader.load(getClass().getResource("timer_interface.fxml"));

            root.getStylesheets().add(getClass().getResource("theme_sheet.css").toExternalForm());

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Timer");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
