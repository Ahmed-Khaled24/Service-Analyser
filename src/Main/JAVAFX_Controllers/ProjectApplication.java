package JAVAFX_Controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("User_Input.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 270);

            stage.setMaxWidth(700);
            stage.setMaxHeight(333);
            stage.setMinWidth(500);
            stage.setMinHeight(200);
            stage.setTitle("User Input");

            stage.setScene(scene);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        stage.getIcons().add(new Image("server.png"));
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}
