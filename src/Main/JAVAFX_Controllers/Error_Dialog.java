package JAVAFX_Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Error_Dialog {
    //------------------------------------------------IDs-----------------------------------------//
    @FXML
    private VBox Error_Pane;
    @FXML
    private Label Error_Label;
    @FXML
    private Button Close_Button;

    //------------------------------------------------Initialization-----------------------------------------//
    public void initialize(){
        // Setting the Label fonts here because importing the font doesnt work in css stylesheet
        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 18);
        Error_Label.setFont(LabelFont);
    }

    //------------------------------------------------Event Handlers-----------------------------------------//
    @FXML
    public void OnButtonClicked(){
        Stage thisStage = (Stage) Error_Pane.getScene().getWindow();
        thisStage.close();
    }
}
