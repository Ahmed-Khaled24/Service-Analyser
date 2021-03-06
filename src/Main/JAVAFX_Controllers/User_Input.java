package JAVAFX_Controllers;

import components.Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.ToggleSwitch;
import utility.Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class User_Input {
    //------------------------------------------------FX IDs-----------------------------------------//
    @FXML
    private Button Browse_Button;
    @FXML
    private Button Submit_Button;
    @FXML
    private Button Clear_Button;
    @FXML
    private ToggleSwitch Mode_Button;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private TextField InputField;

    @FXML
    private Label Input_Label;

    //------------------------------------------------Global Variables-----------------------------------------//

    private String ExcelFile_Path;

    private static Service s1;

    private  Boolean isLightMode = true;


    //------------------------------------------------Initialization-----------------------------------------//
    public void initialize() {







        // Setting the imported font here because it doesn't work properly in the CSS stylesheet
        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 24);
        Input_Label.setFont(LabelFont);

        // setting font for the dark mode toggle switch
        Font ModeFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 13);
        Mode_Button.setFont(ModeFont);
        Mode_Button.setText("Dark Mode");

        // When opening the program the buttons will be disabled to prevent a bug in the listener if statement
        Submit_Button.setDisable(true);
        Clear_Button.setDisable(true);


        InputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String OldText, String NewText) {
                // Disable the Buttons if the newly added change to the TextField is empty or a space
                boolean DisableButton = NewText.isEmpty() || NewText.trim().isEmpty();
                Submit_Button.setDisable(DisableButton);
                Clear_Button.setDisable(DisableButton);
            }
        }); // listens to changes in the textfield to enable or disable the buttons.





    } // Function that occurs automatically at the start of the App

    //------------------------------------------------Event Handlers-----------------------------------------//
    @FXML
    public void handleButtonClick(ActionEvent e) {
        if (e.getSource().equals(Submit_Button)) {
            System.out.println("Submit Pressed");

            // Pass the Filepath from the fileChooser to the constructService method

            System.out.println(ExcelFile_Path);
            s1 = Utility.constructService(ExcelFile_Path);
            System.out.println("Debugger test");

            if(Utility.errorWindowLabel){
                ShowErrorWindow();
            }else if (!s1.getAPIs().isEmpty()) {
                ShowOutputWindow();

            } else {
                ShowErrorWindow();

            }

            System.out.println("All Ok.");


        } else if (e.getSource().equals(Clear_Button)) {// If Clear button is pressed
            InputField.clear();
            Submit_Button.setDisable(true);
            Clear_Button.setDisable(true);
            System.out.println("Clear Pressed");


        } else if (e.getSource().equals(Browse_Button)) { // If Browse button is pressed

            // Creating a file chooser
            FileChooser chooser = new FileChooser();

            // Filtering the open dialogue so you can only choose Excel files with extension .xlsx
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Excel File", "*.xlsx")
            );

            if (ExcelFile_Path != null) {
                String[] Extraction = ExcelFile_Path.split("\\\\");
                ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(Extraction));
                arrayList.remove(arrayList.size() - 1);
                String SavedPath = String.join("\\\\", arrayList);

                chooser.setInitialDirectory(new File(SavedPath));
            }


            File file = chooser.showOpenDialog(MainBorderPane.getScene().getWindow());

            try {
                if (file != null) {
                    ExcelFile_Path = file.getPath();
                    // Printing the FilePath from the FileChooser onto the TextField
                    InputField.setText(ExcelFile_Path);
                    System.out.println(ExcelFile_Path);
                } else {
                    System.out.println("Chooser was cancelled");
                }
            } catch (Exception a) {
                a.getCause();
            }

        }
   } // Event handler for each button on the stage

    @FXML
    private void MoodChange(){

        if(isLightMode){

            MainBorderPane.getScene().getRoot().setStyle("-fx-base:black");
            MainBorderPane.getScene().getStylesheets().add("Dark-Mode.css");


            isLightMode = false;

        }else {

            MainBorderPane.getScene().getStylesheets().remove("Dark-Mode.css");
            MainBorderPane.getScene().getRoot().setStyle("");




            isLightMode=true;
        }
    } // Event handler for the mouse click action of the dark mode toggle switch


    //------------------------------------------------Stage Creators-----------------------------------------//

    public void ShowOutputWindow() {

        // Extracting the file name to pass it as the title of the output window
        String[] Extraction = ExcelFile_Path.split("\\\\");
        String fileName = Extraction[Extraction.length - 1];

        // Create an output window when pressing on submit button
        Stage dialog = new Stage();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("Output_window.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1500, 700);
            scene.getStylesheets().add("Style.css");
            if(!isLightMode) {
                scene.getRoot().setStyle("-fx-base:black");
                scene.getStylesheets().add("Dark-Mode.css");

            }

            dialog.setScene(scene);
            dialog.setTitle(fileName);
            dialog.setMinWidth(1550);
            dialog.setMinHeight(700);


            dialog.initStyle(StageStyle.DECORATED);
            dialog.initModality(Modality.APPLICATION_MODAL); // set the new window modal



        } catch (IOException h) {
            h.printStackTrace();

        } catch (Exception l) {
            System.out.println(l.getMessage());
        }
        // Press F1 to toggle Fullscreen
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.F11.equals(event.getCode())) {
                dialog.setFullScreen(!dialog.isFullScreen());
            }
        });
        dialog.getIcons().add(new Image("server.png"));
        dialog.showAndWait();

    } // Shows the Output window when no bugs are caught

    public  void ShowErrorWindow() {


        Stage ErrorDialog = new Stage();


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("Error_Dialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 150);
            if(!isLightMode) {
                scene.getRoot().setStyle("-fx-base:black");

            }
            ErrorDialog.setScene(scene);
            ErrorDialog.setTitle("Error");
            ErrorDialog.setResizable(false);
            ErrorDialog.initModality(Modality.APPLICATION_MODAL); // set the new window modal



        } catch (IOException g) {
            System.out.println(g.getMessage());
        }
        ErrorDialog.getIcons().add(new Image("cross.png"));
        ErrorDialog.showAndWait();
    } // Shows an error when the service is empty of APIs

    //-----------------------------------Static getter for the ReturnedService-----------------------------------------//
    public static Service getService1(){
        return s1 ;
    }

}



