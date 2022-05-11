package JAVAFX_Controllers;

import components.API;
import components.Service;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import utility.Utility;

public class Output_Window {
    @FXML
    private TextArea RequestArea;
    @FXML
    private TextArea ResponseArea;
    @FXML
    private ListView<API> API_NAMES;

    private  Service ReturnedService;

    private ObservableList<API> Names;


    public void initialize(){

        ReturnedService = Utility.constructService(User_Input.filePath);
        assert ReturnedService != null;

        // Populate the listview with the API arraylist in the service
        API_NAMES.getItems().setAll(ReturnedService.getAPIs());

        // Only on element in the list view can be selected at a time
        API_NAMES.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // The first Item in the list is selected when u open the window for the first time
        API_NAMES.getSelectionModel().selectFirst();









    }


}
