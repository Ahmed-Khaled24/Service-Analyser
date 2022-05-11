package JAVAFX_Controllers;

import components.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import utility.Utility;

import java.io.File;

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

        public  String getExcelFile_Path() {
            return ExcelFile_Path;
        }


        public void initialize(){
            Submit_Button.setDisable(true);
            Clear_Button.setDisable(true);
        }
        @FXML
        public void handleButtonClick(ActionEvent e){
            if(e.getSource().equals(Submit_Button)){
                System.out.println("Submit Pressed");

                String filePath = ExcelFile_Path;
                System.out.println(ExcelFile_Path);
                Service s1 = Utility.constructService(filePath);
                System.out.println("All Ok.");






            } else if(e.getSource().equals(Clear_Button)){
                InputField.clear();
                Submit_Button.setDisable(true);
                Clear_Button.setDisable(true);
                System.out.println("Clear Pressed");

            }else if(e.getSource().equals(Browse_Button)) {
                FileChooser chooser = new FileChooser();

                chooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Excel File", "*.xlsx")
                );
                File file = chooser.showOpenDialog(MainGridPane.getScene().getWindow());

                try {
                    if (file != null) {
                        ExcelFile_Path = file.getPath();
                        InputField.setText(ExcelFile_Path);
                        Submit_Button.setDisable(false);
                        Clear_Button.setDisable(false);
                        System.out.println(ExcelFile_Path);
                    } else {
                        System.out.println("Chooser was cancelled");
                    }
                } catch (Exception a) {
                    a.getCause();
                }
            }


        }
        @FXML
        public void handleKeyReleased(){
            String text = InputField.getText();
            Boolean DisableButton = text.isEmpty() || text.trim().isEmpty() ;
            Submit_Button.setDisable(DisableButton);
            Clear_Button.setDisable(DisableButton);
        }
    }

