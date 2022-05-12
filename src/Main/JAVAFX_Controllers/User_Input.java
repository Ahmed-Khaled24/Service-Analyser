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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.Utility;

import java.io.File;
import java.io.IOException;

public class User_Input {
        @FXML
        private Button Browse_Button;
        @FXML
        private Button Submit_Button;
        @FXML
        private Button Clear_Button;
        @FXML
        private GridPane MainGridPane;
        @FXML
        private TextField InputField;

        private  String ExcelFile_Path;

        public static String filePath;
        @FXML
        private Label test;



        public void initialize(){

            Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 24);
            test.setFont(LabelFont);





            // When opening the program the buttons will be disabled to prevent a bug in the listener if statement
            Submit_Button.setDisable(true);
            Clear_Button.setDisable(true);



            InputField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String OldText, String NewText) {
                    // Disable the Buttons if the newly added change to the TextField is empty or a space
                    Boolean DisableButton = NewText.isEmpty() || NewText.trim().isEmpty() ;
                    Submit_Button.setDisable(DisableButton);
                    Clear_Button.setDisable(DisableButton);
                }
            });



        }
        @FXML
        public void handleButtonClick(ActionEvent e){
            if(e.getSource().equals(Submit_Button)){
                System.out.println("Submit Pressed");

                // Pass the Filepath from the fileChooser to the constructService method
                filePath = ExcelFile_Path;
                System.out.println(ExcelFile_Path);
                Service s1 = Utility.constructService(filePath);

                System.out.println("All Ok.");

                // Extracting the file name to pass it as the title of the output window
                String[] Extraction = filePath.split("\\\\");
                String fileName = Extraction[Extraction.length-1];

                // Create an output window when pressing on submit button
                Stage dialog = new Stage();
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("Output_window.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1500, 600);
                    dialog.setScene(scene);
                    dialog.setTitle(fileName);
                    dialog.setMaximized(true);


                    dialog.initStyle(StageStyle.DECORATED);
                    dialog.initModality(Modality.APPLICATION_MODAL); // set the new window modal


                }catch (IOException h){
                    h.printStackTrace();

                }catch (Exception l){
                    System.out.println(l.getMessage());
                }
                dialog.showAndWait();







//                Dialog<ButtonType> dialog = new Dialog<>(); // Creates a new modal window
//                dialog.initOwner(MainGridPane.getScene().getWindow());
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("Output_window.fxml"));
//                try{// for the load catch io exception
//                    dialog.getDialogPane().setContent(fxmlLoader.load());
//
//
//                }catch (IOException f){
//                    System.out.println(f.getMessage());
//                    System.out.println("Couldn't load the dialog");
//                    f.printStackTrace();
//
//                }
//                dialog.showAndWait();







            } else if(e.getSource().equals(Clear_Button)){// If Clear button is pressed
                InputField.clear();
                Submit_Button.setDisable(true);
                Clear_Button.setDisable(true);
                System.out.println("Clear Pressed");


            }else if(e.getSource().equals(Browse_Button)) { // If Browse button is pressed
                FileChooser chooser = new FileChooser();

                // Filtering the open dialogue so you can only choose Excel files with extension .xlsx
                chooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Excel File", "*.xlsx")
                );
                File file = chooser.showOpenDialog(MainGridPane.getScene().getWindow());

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
        }


    }

