package JAVAFX_Controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("User_Input.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("User Input");
            stage.setScene(scene);
        } catch (Exception e) {
            e.getCause();
            System.out.println(e.getMessage());
        }
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
