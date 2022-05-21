package JAVAFX_Controllers;

import components.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utility.Utility;

public class Error_Dialog {
    //------------------------------------------------IDs-----------------------------------------//
    @FXML
    private VBox Error_Pane;
    @FXML
    private Label Error_Label;
    @FXML
    private Label Hint_Label;
    @FXML
    private Button Close_Button;

    //------------------------------------------------Initialization-----------------------------------------//
    public void initialize(){
        // Setting the Label fonts here because importing the font doesn't work in css stylesheet
        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 18);
        Error_Label.setFont(LabelFont);

        Font HintFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 14);
        Hint_Label.setFont(HintFont);

        Service ReturnedService = User_Input.getService1();
        if(Utility.errorWindowLabel){
            String CurrentAPI = ReturnedService.getAPIs().get(ReturnedService.getAPIs().size() - 1).getName();
            Error_Label.setText("Error in "+CurrentAPI+" data rows");
            Hint_Label.setText("Check your excel file");
            Utility.errorWindowLabel = false;

        }else{
            Hint_Label.setText("Your excel file is empty");
        }
    }

    //------------------------------------------------Event Handlers-----------------------------------------//
    @FXML
    public void OnButtonClicked(){
        Stage thisStage = (Stage) Error_Pane.getScene().getWindow();
        thisStage.close();
    }
}
