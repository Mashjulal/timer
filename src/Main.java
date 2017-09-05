import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage){
        primaryStage = stage;
        startApplication();
    }

    /**
     * Loads application files and sets its
     * necessary parameters: title, size policy.
     */
    private void startApplication(){
        try {
            // Load app UI from fxml file
            Parent root = FXMLLoader.load(getClass().getResource("timer_interface.fxml"));
            // Add styles from css file
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
