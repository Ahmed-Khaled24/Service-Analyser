package JAVAFX_Controllers;

import components.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class Output_Window {
    @FXML
    private TextArea RequestArea;
    @FXML
    private TextArea ResponseArea;
    @FXML
    private ListView<String> API_NAMES;

    private static Service ReturnedService;

    public static void setServiceForJavaFX(Service service){
        ReturnedService=service;
    }
    public void initialize(){

    }


}
